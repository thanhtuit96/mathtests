package tdt.it.mathtests.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tdt.it.mathtests.common.DeviceProvider;
import tdt.it.mathtests.models.Authority;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.models.UserTokenState;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.security.auth.JwtAuthenticationRequest;
import tdt.it.mathtests.service.impl.AuthorityServiceImpl;
import tdt.it.mathtests.service.impl.CustomUserDetailsServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fan.jin on 2017-05-10.
 */

@RestController
@RequestMapping( value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    
	@Autowired
    TokenHelper tokenHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private AuthorityServiceImpl authorityService;

    @Autowired
    private DeviceProvider deviceProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest,
            HttpServletResponse response,
            Device device
    ) throws AuthenticationException, IOException {

    	logger.info(String.format("Attempting login for user [%s]", authenticationRequest.getUsername()));
     
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        // Inject into security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // token creation
        User user = (User)authentication.getPrincipal();
        String jws = tokenHelper.generateToken( user.getUsername(),user.getAuthorities().toString(), device);
        int expiresIn = tokenHelper.getExpiredIn(device);
        userDetailsService.changeLastAccess();
        // Return the token
        return ResponseEntity.ok(new UserTokenState(jws, expiresIn));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshAuthenticationToken(
            HttpServletRequest request,
            HttpServletResponse response,
            Principal principal
            ) {

        String authToken = tokenHelper.getToken( request );

        Device device = deviceProvider.getCurrentDevice(request);

        if (authToken != null && principal != null) {

            // TODO check user password last update
            String refreshedToken = tokenHelper.refreshToken(authToken, device);
            int expiresIn = tokenHelper.getExpiredIn(device);

            return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn));
        } else {
            UserTokenState userTokenState = new UserTokenState();
            return ResponseEntity.accepted().body(userTokenState);
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
        userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
        Map<String, String> result = new HashMap<>();
        result.put( "result", "success" );
        return ResponseEntity.accepted().body(result);
    }
    
    @RequestMapping(value = "/registryUser", method = RequestMethod.POST)
    public ResponseEntity<?> registryUser(@RequestBody Map<String,String> body){
    	String userName = body.get("username");
    	String password = body.get("password");
    	String firstName = body.get("firstName");
    	String lastName = body.get("lastName");
    	String email = body.get("email");
    	String birthDate = body.get("birthDate");
    	String authorities = body.get("authorities");
  
    	User newUser = new User();
    	newUser.setUsername(userName);
    	newUser.setPassword(password);
    	newUser.setFirstName(firstName);
    	newUser.setLastName(lastName);
    	newUser.setEmail(email);
    	newUser.setBirthDate(Date.valueOf(birthDate));
    
    	List<Authority> list = new ArrayList<Authority>();
    	if(authorities != null) {
	    	for(String auth : authorities.split(",")) {
	    		list.add(authorityService.getAuthorityByName(auth));
	    	}
    	}
        Map<String, String> result = new HashMap<>();
    	newUser.setAuthorities(list);
    	if(validate(newUser)) {
    		userDetailsService.registryUser(newUser);
            result.put( "result", "success" );
            return ResponseEntity.accepted().body(result);    	    	
    	} else {
            result.put( "result", "fail" );
            return ResponseEntity.badRequest().body(result);
    	}
    }
    
  
      
    public boolean validate(User u) {    	
    	return (u.getUsername() != null) 
    			&& (u.getPassword() != null)
    			&& (u.getFirstName() != null)
    			&& (u.getLastName() != null)
    			&& (u.getEmail() != null)
    			&& (u.getBirthDate() != null)
    			&& (u.getAuthorities().size() > 0);
    }
    
    static class PasswordChanger {
        public String oldPassword;
        public String newPassword;
    }
}