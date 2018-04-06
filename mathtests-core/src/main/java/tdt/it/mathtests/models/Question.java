package tdt.it.mathtests.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
    @JsonIgnore
	@Column(name="ANSWER_CORRECT")
    private int answerCorrect;
    
    @JsonIgnore
	@Column(name="ANSWER_CORRECT_CONTENT")
    private String answerCorrectContent;
	
	@ManyToOne
	@JoinColumn(name="ID_USER")
    @JsonBackReference
	private User owner;

	@Column(name = "IS_PUBLIC")
	private boolean publiced;

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

	
}