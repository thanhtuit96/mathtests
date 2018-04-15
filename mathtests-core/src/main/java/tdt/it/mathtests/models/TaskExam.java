package tdt.it.mathtests.models;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="M_TASK_DETAIL")
public class TaskExam implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2823018059006526410L;

	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TASK_ID")
	private Task task;
	
	@EmbeddedId
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="QUESTION_ID")
	private Question question;
	
	@Basic
	@Column(name="ANSWER")
	private int answer;
	
	public TaskExam() {
		// TODO Auto-generated constructor stub
	}
	
	public TaskExam(Task t, Question q, int ans) {
		// TODO Auto-generated constructor stub
		this.task = t;
		this.question = q;
		this.answer = ans;
	}
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return this.answer == this.question.getAnswerCorrect();
	}
	/*@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.task.hashCode() * this.question.hashCode();
	}*/
	@Override
	public boolean equals(Object arg0) {
		if(arg0 == null)
			return false;
		if(!(arg0 instanceof TaskExam))
			return false;
		TaskExam other = (TaskExam) arg0;
		return this.task.getId() == other.getTask().getId() && this.question.getId() == other.getQuestion().getId();
	}
}
