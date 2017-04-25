package mum.sched.student.service;

import java.util.List;

import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Student;
import mum.sched.entity.model.User;

public interface StudentService {
	List<Student> getAllStudents();
	
	List<Entry> getAllEntries();
	
	List<User> getUserByUsername(String username);
	
	Student saveStudent(Student student);
	
	User saveUser(User user);
	
	Student getStudentByID(int studentID);
}
