package tdt.it.mathtests.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdt.it.mathtests.models.Question;

@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long>{
	public List<Question> findQuestionByPubliced(boolean publiced);
	public Question findQuestionById(long id);
}
