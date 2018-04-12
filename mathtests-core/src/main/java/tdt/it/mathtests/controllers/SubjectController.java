package tdt.it.mathtests.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tdt.it.mathtests.models.Group;
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.models.Subject;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.service.impl.CustomUserDetailsServiceImpl;
import tdt.it.mathtests.service.impl.GroupServiceImpl;
import tdt.it.mathtests.service.impl.QuestionServiceImpl;
import tdt.it.mathtests.service.impl.SubjectServiceImpl;

@RestController
@RequestMapping( value = "/api/subjects", produces = MediaType.APPLICATION_JSON_VALUE )
public class SubjectController {

	@Autowired
    TokenHelper tokenHelper;
	
	@Autowired
	private GroupServiceImpl groupServiceImpl;
	
	@Autowired
	private SubjectServiceImpl subjectServiceImpl;
	
	
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
  
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Subject newSubject(
    		@RequestBody Map<String,String> body){
		String idGroup = body.get("idGroup");
		String content = body.get("name");
		return subjectServiceImpl.addSubject(Long.parseLong(idGroup), content);	    	
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Subject updateSubject(@RequestBody Map<String,String> body){
    	String id = body.get("id");
    	String content = body.get("name");
    	return subjectServiceImpl.updateSubject(Long.parseLong(id), content);	    	
    }
    
    
    @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Group addQuestion(){
    	return null;   	    	
    }
}
