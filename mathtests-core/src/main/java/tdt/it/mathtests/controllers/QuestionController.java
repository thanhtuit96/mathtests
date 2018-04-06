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
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.service.impl.CustomUserDetailsServiceImpl;
import tdt.it.mathtests.service.impl.QuestionServiceImpl;

@RestController
@RequestMapping( value = "/api/questions", produces = MediaType.APPLICATION_JSON_VALUE )
public class QuestionController {

	@Autowired
    TokenHelper tokenHelper;
	@Autowired
	private QuestionServiceImpl questionServiceImpl;
	
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
	     
    @RequestMapping( method = GET, value = "/all" )
	@PreAuthorize("hasRole('SUPERVISOR')")
	public List<Question> getQuestions() {
	    return (List<Question>) questionServiceImpl.getQuestionPublish();
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> addQuestion(@RequestHeader Map<String,String> header,
    									  @RequestBody Map<String,String> body){
    	String content = body.get("content");
    	String answerA = body.get("answerA");
    	String answerB = body.get("answerB");
    	String answerC = body.get("answerC");
    	String answerD = body.get("answerD");
    	int answerCorrect = Integer.parseInt(body.get("answerCorrect"));
    	String answerCorrectContent = body.get("answerCorrectContent");
        boolean publiced = Boolean.parseBoolean(body.get("publiced"));
      	String username = tokenHelper.getUsernameFromToken(header.get("Authorization").substring(7));
      	User owner = (User) userDetailsService.loadUserByUsername(username);
      	questionServiceImpl.addQuestion(content, answerA, answerB, answerC, answerD, answerCorrect, answerCorrectContent, owner, publiced);
        return ResponseEntity.accepted().body("success");    	    	
    }
       
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public ResponseEntity<?> updateQuestion(@RequestHeader Map<String,String> header,
    									  @RequestBody Map<String,String> body){
    	long oldId = Long.parseLong(body.get("id"));
    	String content = body.get("content");
    	String answerA = body.get("answerA");
    	String answerB = body.get("answerB");
    	String answerC = body.get("answerC");
    	String answerD = body.get("answerD");
    	int answerCorrect = Integer.parseInt(body.get("answerCorrect"));
    	String answerCorrectContent = body.get("answerCorrectContent");
        boolean publiced = Boolean.parseBoolean(body.get("publiced"));
      	String username = tokenHelper.getUsernameFromToken(header.get("Authorization").substring(7));
      	User owner = (User) userDetailsService.loadUserByUsername(username);
      	
      	if(questionServiceImpl.checkQuestionOfOwner(oldId, owner)) {
      		questionServiceImpl.updateQuestion(oldId, content, answerA, answerB, answerC, answerD, answerCorrect, answerCorrectContent, publiced);	
      		return ResponseEntity.accepted().body("success");    	    	
      	} else {
      		return ResponseEntity.accepted().body("fail");
      	}
    }
    
}
