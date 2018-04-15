package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdt.it.mathtests.models.Exam;

@Repository
public interface ExamRepository  extends JpaRepository<Exam, Long>{
	public Exam findExamById(long id);
}
