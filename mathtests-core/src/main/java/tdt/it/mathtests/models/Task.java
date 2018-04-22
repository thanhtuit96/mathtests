package tdt.it.mathtests.models;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="M_TASKS")
public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="EXAM_ID")
	private Exam exam;
	
	@Column(name="TIME_IN")
    private Timestamp timeIn;
	
	@Column(name="TIME_UT")
    private Timestamp timeUp;
    
	@Column(name="SCORE")
    private int score;
    
	@JsonIgnore
	@OneToMany(mappedBy="task")
    @JsonManagedReference
	private Set<TaskExam> taskDetail;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(User u, Exam e, Timestamp timeIn) {
		this.user = u;
		this.exam = e;
		this.timeIn = timeIn;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Timestamp getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Timestamp timeIn) {
		this.timeIn = timeIn;
	}

	public Timestamp getTimeUp() {
		return timeUp;
	}

	public void setTimeUp(Timestamp timeUp) {
		this.timeUp = timeUp;
	}

	public int getScore() {
		return score;
	}

	public Set<TaskExam> getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(Set<TaskExam> taskDetail) {
		this.taskDetail = taskDetail;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id);
	}
	
}
