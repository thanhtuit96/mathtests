package tdt.it.mathtests.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tdt.it.mathtests.models.Exam;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.service.impl.ExamServiceImpl;

@RestController
@RequestMapping( value = "/api/exams", produces = MediaType.APPLICATION_JSON_VALUE )
public class ExamController {

	@Autowired
    TokenHelper tokenHelper;
	
	@Autowired
	private ExamServiceImpl examServiceImpl;
		
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Exam newSubject(
    		@RequestBody Map<String,String> body){
		String idSubject = body.get("idSubject");
		String content = body.get("name");
		String timeleft = body.get("timeLeft");
		return examServiceImpl.addExam(Long.parseLong(idSubject), content, Integer.parseInt(timeleft));    	
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Exam updateSubject(@RequestBody Map<String,String> body){
    	String id = body.get("id");
    	String content = body.get("name");
		String timeleft = body.get("timeLeft");
    	return examServiceImpl.updateExam(Long.parseLong(id), content, Integer.parseInt(timeleft));	    	
    }
    
    @RequestMapping(value = "/question", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPERVISOR')")
    public Exam updateQuestion(@RequestBody Map<String,String> body){
    	String id = body.get("idExam");
    	String idQuestion = body.get("idQuestion");
    	List<Long> listId = new ArrayList<Long>();
    	for(String s: idQuestion.split(",")) {
    		listId.add(Long.parseLong(s));
    	}
    	return examServiceImpl.updateQuestion(Long.parseLong(id), listId);	    	
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
    public List<Exam> addQuestion(@RequestBody Map<String,String> body){
    	String id = body.get("id");
    	return examServiceImpl.getAll(Integer.parseInt(id));   	    	
    }
}
