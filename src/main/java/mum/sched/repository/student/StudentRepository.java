package mum.sched.repository.student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	public Student findById(int id);
	
	public List<Student> findByStudentNumber(String studentNumber);
}
