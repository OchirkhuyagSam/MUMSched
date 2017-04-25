package mum.sched.actor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.entity.model.Administrator;

public interface AdminRepository extends JpaRepository<Administrator, Integer> {
	public Administrator findById(int id);
}
