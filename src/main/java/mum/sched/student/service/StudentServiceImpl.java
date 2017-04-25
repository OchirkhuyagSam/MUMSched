package mum.sched.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Student;
import mum.sched.entity.model.User;
import mum.sched.entry.repository.EntryRepository;
import mum.sched.student.repository.StudentRepository;
import mum.sched.user.repository.UserRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

	@Override
	public List<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Student getStudentByID(int studentID) {
		return studentRepository.findById(studentID);
	}
}
