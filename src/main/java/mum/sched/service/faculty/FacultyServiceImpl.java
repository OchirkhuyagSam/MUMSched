package mum.sched.service.faculty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.model.entity.Course;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.User;
import mum.sched.repository.course.CourseRepository;
import mum.sched.repository.faculty.FacultyRepository;
import mum.sched.repository.user.UserRepository;

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

//	@Override
//	public List<Faculty> getFacultyByFacultyNumber(String facultyNumber) {
//		return facultyRepository.findByFacultyNumber(facultyNumber);
//	}

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

	@Override
	public boolean isValidFaculty(String facultyNumber) {
		List<Faculty> faculties = facultyRepository.findByFacultyNumber(facultyNumber);
		return (faculties.size() == 0);
	}

}
