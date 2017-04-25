package mum.sched.faculty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.course.repository.CourseRepository;
import mum.sched.entity.model.Course;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.User;
import mum.sched.faculty.repository.FacultyRepository;
import mum.sched.user.repository.UserRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Faculty> getAllFaculties() {
		return facultyRepository.findAll();
	}

	@Override
	public List<Faculty> getFacultyByFacultyNumber(String facultyNumber) {
		return facultyRepository.findByFacultyNumber(facultyNumber);
	}

	@Override
	public Faculty saveFaculty(Faculty faculty) {
		return facultyRepository.save(faculty);
	}

	@Override
	public Faculty getFacultyById(int id) {
		return facultyRepository.findById(id);
	}

	@Override
	public List<Course> getCoursesNotInFaculty(int facultyID) {
		return courseRepository.findCoursesNotInFaculty(facultyID);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

}
