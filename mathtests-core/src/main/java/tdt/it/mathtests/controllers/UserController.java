package tdt.it.mathtests.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tdt.it.mathtests.models.Authority;
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.service.impl.CustomUserDetailsServiceImpl;

@RestController
@RequestMapping( value = "/user", produces = MediaType.APPLICATION_JSON_VALUE )
public class UserController {

	 
	@Autowired
	private TokenHelper tokenHelper;
		
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
    
	@RequestMapping( method = GET, value = "/info" )
	@PreAuthorize("hasRole('USER')")
	public User getInfomation(           
	  			HttpServletRequest request,
	              HttpServletResponse response) {
	    String token = tokenHelper.getToken(request);
	    String username = tokenHelper.getUsernameFromToken(token);
	  	return (User) userDetailsService.loadUserByUsername(username);
	 }
	      
	 @RequestMapping( method = GET, value = "/questions" )
	 @PreAuthorize("hasRole('SUPERVISOR')")
	 public List<Question> getQuestions(           
	  			HttpServletRequest request,
	              HttpServletResponse response) {
	    String token = tokenHelper.getToken(request);
	    String username = tokenHelper.getUsernameFromToken(token);
	  	return (List<Question>) ((User) userDetailsService.loadUserByUsername(username)).getQuestions();
	 }
	    
	@RequestMapping(value = "/all", method = GET)
	@PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll(HttpServletRequest request,
            HttpServletResponse response){
		String token = tokenHelper.getToken(request);
	    String role = tokenHelper.getRoleFromToken(token);
	    
	    if(role.contains("SUPERADMIN")) {
	    	return userDetailsService.getAll().stream().filter(o -> o.getAuthorities().size() < 4).collect(Collectors.toList());	  	    	
	    } else {
	    	return userDetailsService.getAll().stream().filter(o -> o.getAuthorities().size() < 3).collect(Collectors.toList());	  	    	
	    }   
    }
	
	@RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
    public User userUpdate(@RequestHeader Map<String,String> header,
    						@RequestBody Map<String,String> body){
    	String username = tokenHelper.getUsernameFromToken(header.get("authorization").substring(7));
    	System.out.println(username);
    	String firstName = body.get("firstName");
    	String lastName = body.get("lastName");
    	String email = body.get("email");
    	String birthDate = body.get("birthDate");
    	String enable = body.get("enabled");
    	System.out.println(enable);
    	//String authorities = body.get("authorities");
    	User u = (User) userDetailsService.loadUserByUsername(username);
    	u.setFirstName(firstName);
    	u.setLastName(lastName);
    	u.setEmail(email);
    	u.setBirthDate(Date.valueOf(birthDate));
    	u.setEnabled(Boolean.parseBoolean(enable));
    	userDetailsService.updateUser(u);
        return (User) userDetailsService.loadUserById(u.getId());
    }

    @RequestMapping(value = "/updates", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@RequestBody Map<String,String> body){
    	String id = body.get("id");
    	String firstName = body.get("firstName");
    	String lastName = body.get("lastName");
    	String email = body.get("email");
    	String birthDate = body.get("birthDate");
    	String enable = body.get("enabled");
    	System.out.println(enable);
    	//String authorities = body.get("authorities");
    	User u = (User)userDetailsService.loadUserById(Integer.parseInt(id));
    	u.setFirstName(firstName);
    	u.setLastName(lastName);
    	u.setEmail(email);
    	u.setBirthDate(Date.valueOf(birthDate));
    	u.setEnabled(Boolean.parseBoolean(enable));
    	userDetailsService.updateUser(u);
        return (User) userDetailsService.loadUserById(Integer.parseInt(id));
    }
}
