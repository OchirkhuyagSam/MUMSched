package mum.sched.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> findByUsernameAndPassword(String username, String password);
	
	public List<User> findByUsername(String username);
}
