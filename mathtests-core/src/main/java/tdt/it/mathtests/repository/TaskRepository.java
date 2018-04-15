package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdt.it.mathtests.models.Task;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long>{
	public Task findTaskById(long id);
}
