package tdt.it.mathtests.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public List<User> getAll(){
    	return userDetailsService.getAll().stream().filter(o -> o.getAuthorities().size() < 4).collect(Collectors.toList());	  	    	
    }
	
    @RequestMapping(value = "/deactived", method = POST)
	@PreAuthorize("hasRole('ADMIN')")
    public void addQuestion(@RequestBody Map<String,String> body){
    	long userId = Long.parseLong(body.get("id"));
    	
    	  	    	
    }
}
