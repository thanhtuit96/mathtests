package tdt.it.mathtests.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="M_QUESTION")
public class Question implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1845553943782300367L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="CONTENT")
    private String content;

	@Column(name="ANSWER_A")
    private String answerA;
	
	@Column(name="ANSWER_B")
    private String answerB;
	
	@Column(name="ANSWER_C")
    private String answerC;
	
	@Column(name="ANSWER_D")
    private String answerD;
	
	@Column(name="ANSWER_CORRECT")
    private int answerCorrect;
    
	@Column(name="ANSWER_CORRECT_CONTENT")
    private String answerCorrectContent;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
	private User owner;

	@Column(name = "IS_PUBLIC")
	private boolean publiced;

	@JsonIgnore
	@OneToMany(mappedBy="question")
	private Set<TaskExam> taskDetail;
	
	@JsonIgnore
	@ManyToMany(mappedBy="question")
	private List<Exam> exams;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public Question(String content, String answerA, String answerB, String answerC, String answerD, int answerCorrect,
			String answerCorrectContent, User owner, boolean publiced) {
		super();
		this.content = content;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.answerCorrect = answerCorrect;
		this.answerCorrectContent = answerCorrectContent;
		this.owner = owner;
		this.publiced = publiced;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}

	public int getAnswerCorrect() {
		return answerCorrect;
	}

	public void setAnswerCorrect(int answerCorrect) {
		this.answerCorrect = answerCorrect;
	}

	public String getAnswerCorrectContent() {
		return answerCorrectContent;
	}

	public void setAnswerCorrectContent(String answerCorrectContent) {
		this.answerCorrectContent = answerCorrectContent;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isPubliced() {
		return publiced;
	}

	public void setPubliced(boolean publiced) {
		this.publiced = publiced;
	}

	public Set<TaskExam> getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(Set<TaskExam> taskDetail) {
		this.taskDetail = taskDetail;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id);
	}

	

	
}
