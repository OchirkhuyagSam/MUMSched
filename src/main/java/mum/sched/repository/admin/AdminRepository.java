package mum.sched.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.Administrator;

public interface AdminRepository extends JpaRepository<Administrator, Integer> {
	public Administrator findById(int id);
}
