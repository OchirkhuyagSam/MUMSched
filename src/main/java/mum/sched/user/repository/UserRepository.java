package mum.sched.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.entity.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> findByUsernameAndPassword(String username, String password);
	
	public List<User> findByUsername(String username);
}
