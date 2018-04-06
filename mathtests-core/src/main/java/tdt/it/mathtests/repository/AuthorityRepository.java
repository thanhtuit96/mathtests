package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdt.it.mathtests.models.Authority;

@Repository
public interface AuthorityRepository  extends JpaRepository<Authority, Long>{
	public Authority findByName(String name);
}
