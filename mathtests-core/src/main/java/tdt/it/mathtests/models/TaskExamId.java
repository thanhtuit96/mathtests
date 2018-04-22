package tdt.it.mathtests.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
public class TaskExamId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759760935490394079L;

	@Column(name="TASK_ID")
	private long taskId;
	
	@Column(name="QUESTION_ID")
	private long questionId;
	
	public TaskExamId() {
		// TODO Auto-generated constructor stub
	}

	public TaskExamId(
			long taskId,
			long questionId) {
		this.taskId = taskId;
		this.questionId = questionId;
		// TODO Auto-generated constructor stub
	}
	
    public long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        TaskExamId that = (TaskExamId) o;
        return Objects.equals(taskId, that.taskId) && 
               Objects.equals(questionId, that.questionId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(taskId, questionId);
    }
}
