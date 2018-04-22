package tdt.it.mathtests.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="M_EXAM")
public class Exam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4082205729449611381L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="TIME_LEFT")
	private int timeLeft;
	
	@Column(name="DATE_IN")
	private Timestamp dateIn;
	
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID")
    @JsonBackReference
	private Subject subject;
	
	@JsonIgnore
    @OneToMany(mappedBy="exam")
    private List<Task> tasks;
	
	@JsonIgnore
	@ManyToMany
    @JoinTable(name = "M_EXAM_QUESTION",
            joinColumns = @JoinColumn(name = "EXAM_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID"))
	private List<Question> question;
	
	public Exam() {
		// TODO Auto-generated constructor stub
	}

	public Exam(String title,int timeleft, Subject s) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.timeLeft = timeleft;
		this.subject = s;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public Timestamp getDateIn() {
		return dateIn;
	}

	public void setDateIn(Timestamp dateIn) {
		this.dateIn = dateIn;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	/*public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}*/
	
	
}
