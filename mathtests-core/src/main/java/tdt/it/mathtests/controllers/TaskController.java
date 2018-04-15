package tdt.it.mathtests.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tdt.it.mathtests.models.Exam;
import tdt.it.mathtests.models.Task;
import tdt.it.mathtests.security.TokenHelper;
import tdt.it.mathtests.service.impl.ExamServiceImpl;
import tdt.it.mathtests.service.impl.TaskServiceImpl;

@RestController
@RequestMapping( value = "/api/tasks", produces = MediaType.APPLICATION_JSON_VALUE )
public class TaskController {

	@Autowired
    TokenHelper tokenHelper;
	
	@Autowired
	private TaskServiceImpl taskServiceImpl;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
    public List<Task> getTask(@RequestHeader Map<String,String> header){
    	String username = tokenHelper.getUsernameFromToken(header.get("authorization").substring(7));
    	return taskServiceImpl.getAll(username);   	    	
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
    public Task newTask(
    		@RequestHeader Map<String,String> header,
    		@RequestBody Map<String,String> body){
    	//	public Task addTask(String username, long examId)
    	String username = tokenHelper.getUsernameFromToken(header.get("authorization").substring(7));
		String idTask = body.get("examId");
		return taskServiceImpl.addTask(username, Integer.parseInt(idTask));    	
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
    public Task updateSubject(@RequestBody Map<String,String> body){
    	//	public Task update(long idTask, List<String> answer) {
    	String id = body.get("id");
    	String answer = body.get("answer");
    	List<String> ans = Arrays.asList(answer.split(","));
    	return taskServiceImpl.update(Long.parseLong(id), ans);	    	
    }
    

}
