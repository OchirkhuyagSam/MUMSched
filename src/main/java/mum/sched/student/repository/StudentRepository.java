package mum.sched.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	public Student findById(int id);
	
	public List<Student> findByStudentNumber(String studentNumber);
}
