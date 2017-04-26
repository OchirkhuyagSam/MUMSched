package mum.sched.repository.faculty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
	public Faculty findById(int id);
	
	public List<Faculty> findByFacultyNumber(String facultyNumber);
}
