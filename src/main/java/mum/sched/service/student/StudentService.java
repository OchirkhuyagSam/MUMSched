package mum.sched.service.student;

import java.util.List;

import mum.sched.model.entity.Entry;
import mum.sched.model.entity.Section;
import mum.sched.model.entity.Student;
import mum.sched.model.entity.User;

public interface StudentService {
	List<Student> getAllStudents();
	
	List<Entry> getAllEntries();
	
	List<User> getUserByUsername(String username);
	
	Student saveStudent(Student student);
	
	User saveUser(User user);
	
	Student getStudentByID(int studentID);
	
	String validateNewSection(Student student, Section section);
}
