package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdt.it.mathtests.models.Group;

@Repository
public interface GroupRepository  extends JpaRepository<Group, Long>{
	public Group findGroupById(long id);
}
