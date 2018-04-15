package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdt.it.mathtests.models.Subject;

@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Long>{
	public Subject findSubjectById(long id);
}
