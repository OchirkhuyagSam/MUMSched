package mum.sched.faculty.service;

import java.util.List;

import mum.sched.entity.model.Course;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.User;

public interface FacultyService {
	List<Faculty> getAllFaculties();
	
	List<Faculty> getFacultyByFacultyNumber(String facultyNumber);
	
	Faculty saveFaculty(Faculty faculty);
	
	Faculty getFacultyById(int id);
	
	List<Course> getCoursesNotInFaculty(int facultyID);
	
	User saveUser(User user);
}
