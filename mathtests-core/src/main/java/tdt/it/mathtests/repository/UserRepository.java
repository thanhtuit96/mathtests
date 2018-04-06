package tdt.it.mathtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdt.it.mathtests.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);
    public User findById(Long id);
}
