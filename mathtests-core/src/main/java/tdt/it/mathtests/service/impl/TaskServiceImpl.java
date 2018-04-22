package tdt.it.mathtests.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdt.it.mathtests.models.Exam;
import tdt.it.mathtests.models.Group;
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.models.Task;
import tdt.it.mathtests.models.TaskExam;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.repository.ExamRepository;
import tdt.it.mathtests.repository.GroupRepository;
import tdt.it.mathtests.repository.QuestionRepository;
import tdt.it.mathtests.repository.TaskDetailRepository;
import tdt.it.mathtests.repository.TaskRepository;
import tdt.it.mathtests.repository.UserRepository;

@Service
public class TaskServiceImpl{

    @Autowired
	private TaskRepository taskRepository;
    
    @Autowired
	private TaskDetailRepository taskDetailRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
   	private ExamRepository examRepository;
    
    @Autowired
   	private QuestionRepository questionRepository;
    
	public List<Task> getAll(String username) {
		User u = userRepository.findByUsername(username);
		return u.getTasks();
	}
	
	public Task addTask(String username, long examId) {
		User u = userRepository.findByUsername(username);
		Exam e = examRepository.findExamById(examId);
		Task task = new Task(u, e, new Timestamp(System.currentTimeMillis()));
		taskRepository.save(task);
		int timeLeft = task.getExam().getTimeLeft();
		if(timeLeft > 0) {
			try {
				Thread.sleep((timeLeft+1)*60);
				Task task2 = taskRepository.findTaskById(task.getId());
				if(task2.getTimeUp() == null);
				task2.setTimeUp(new Timestamp(System.currentTimeMillis()));
				taskRepository.save(task2);
				
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		return task;
	}

	public Task update(long idTask, List<String> answer) {
		Task task = taskRepository.findTaskById(idTask);
		int timeLeft = task.getExam().getTimeLeft();
		if(timeLeft == 0)
			task.setTimeUp(new Timestamp(System.currentTimeMillis()));
		else {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Timestamp timeMake = new Timestamp(task.getTimeIn().getTime() + (long)timeLeft*60*1000);
			if(timeMake.before(now))
				return null;
			else 
				task.setTimeUp(new Timestamp(System.currentTimeMillis()));
		}
		Set<TaskExam> taskDetail = new LinkedHashSet<TaskExam>();
		String a[];
		for(String ans : answer) {
			a=ans.split("-");
			Question q = questionRepository.findQuestionById(Long.parseLong(a[0]));
			taskDetail.add(new TaskExam(task, q, Integer.parseInt(a[1])));
		}
		for(TaskExam t : taskDetail) {
			taskDetailRepository.save(t);
		}
		int score = (int) task.getTaskDetail().stream().filter(o->o.isCorrect()).count();
		task.setScore(score);
		taskRepository.save(task);
		return task;
	}
	
}
