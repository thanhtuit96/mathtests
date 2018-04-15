package tdt.it.mathtests.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdt.it.mathtests.models.Exam;
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.repository.ExamRepository;
import tdt.it.mathtests.repository.QuestionRepository;
import tdt.it.mathtests.repository.SubjectRepository;

@Service
public class ExamServiceImpl{

    @Autowired
	private ExamRepository examRepository;
    
    @Autowired
   	private SubjectRepository subjectRepository;
	
    @Autowired
   	private QuestionRepository questionRepository;
    
	public Exam addExam(long subjectid,String title, int timeleft) {
		Exam e = new Exam(title, timeleft, subjectRepository.findSubjectById(subjectid));
		e.setDateIn(new Timestamp(System.currentTimeMillis()));
		examRepository.save(e);
		return e;
	}

	public List<Exam> getAll(long subjectID){
		return subjectRepository.findSubjectById(subjectID).getExam();
	}
	
	public Exam updateExam(long id, String title, int timeleft) {
		Exam e = examRepository.findExamById(id);
		e.setTitle(title);
		e.setTimeLeft(timeleft);
		examRepository.save(e);
		return e;
	}
	
	public Exam updateQuestion(long idExam,List<Long> idQuestion) {
		Exam e = examRepository.findExamById(idExam);
		List<Question> l = new ArrayList<Question>();
		for(long id: idQuestion)
			l.add(questionRepository.findQuestionById(id));
		e.setQuestion(l);
		examRepository.save(e);
		return e;
	}
	
}
