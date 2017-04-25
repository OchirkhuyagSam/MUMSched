package mum.sched.faculty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.entity.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	public Faculty findById(int id);
	
	public List<Faculty> findByFacultyNumber(String facultyNumber);
}
