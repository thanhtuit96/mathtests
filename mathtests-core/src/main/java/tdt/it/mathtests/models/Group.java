package tdt.it.mathtests.models;

import java.io.Serializable;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="M_GROUP")
public class Group implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4082205729449611381L;

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NAME")
    private String name;

	@ManyToOne
	@JoinColumn(name="TEACHER")
    @JsonBackReference
	private User teacher;

	@OneToMany(mappedBy="group", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Subject> subjects;
    
	public Group() {
		// TODO Auto-generated constructor stub
	}
	
	public Group(String title, User u) {
		// TODO Auto-generated constructor stub
		this.name = title;
		this.teacher = u;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	public void addSubjects(Subject subject) {
		subjects.add(subject);
	}
	
}
