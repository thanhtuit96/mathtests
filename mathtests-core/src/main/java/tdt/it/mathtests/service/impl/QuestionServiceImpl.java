package tdt.it.mathtests.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdt.it.mathtests.models.Question;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.repository.QuestionRepository;
import tdt.it.mathtests.repository.UserRepository;

@Service
public class QuestionServiceImpl{

    @Autowired
	private QuestionRepository questionRepository;
    
    @Autowired
    private UserRepository userRepository;

    
	public List<Question> getQuestionPublish() {
		// TODO Auto-generated method stub
		return ( List<Question> ) questionRepository.findQuestionByPubliced(true); 
	}
	
	public List<Question> getQuestionOwner(String username) {
		// TODO Auto-generated method stub
		return ( List<Question> ) userRepository.findByUsername(username).getQuestions(); 
	}
	
	public Question getQuestionById(long id) {
		return questionRepository.findQuestionById(id);
	}
	
	public Question addQuestion(String content, String answerA, String answerB, String answerC, String answerD, int answerCorrect,
			String answerCorrectContent, User owner, boolean publiced) {
		Question q = new Question(content, answerA, answerB, answerC, answerD, answerCorrect, answerCorrectContent, owner, publiced);
		questionRepository.save(q);
		return q;
	}
	
	public Question updateQuestion(long oldId,String content, String answerA, String answerB, String answerC, String answerD, int answerCorrect,
			String answerCorrectContent, boolean publiced) {
		
		Question q = questionRepository.findQuestionById(oldId);
		q.setContent(content);
		q.setAnswerA(answerA);
		q.setAnswerB(answerB);
		q.setAnswerC(answerC);
		q.setAnswerD(answerD);
		q.setAnswerCorrect(answerCorrect);
		q.setAnswerCorrectContent(answerCorrectContent);
		q.setPubliced(publiced);
		questionRepository.save(q);
		return q;
	}
	
	public boolean checkQuestionOfOwner(long idQuestion,User owner) {
		if (questionRepository.findQuestionById(idQuestion) == null)
			return false;
		return owner.getId() == questionRepository.findQuestionById(idQuestion).getOwner().getId();
	}

}
