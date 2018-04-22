package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdt.it.mathtests.models.Task;
import tdt.it.mathtests.models.TaskExam;

@Repository
public interface TaskDetailRepository  extends JpaRepository<TaskExam, Long>{
	public Task findTaskById(long id);
}
