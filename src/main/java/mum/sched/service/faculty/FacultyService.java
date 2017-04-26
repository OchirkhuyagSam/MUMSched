package mum.sched.service.faculty;

import java.util.List;

import mum.sched.model.entity.Course;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.User;

public interface FacultyService {
	List<Faculty> getAllFaculties();
	
	//List<Faculty> getFacultyByFacultyNumber(String facultyNumber);
	
	Faculty saveFaculty(Faculty faculty);
	
	Faculty getFacultyById(int id);
	
	List<Course> getCoursesNotInFaculty(int facultyID);
	
	User saveUser(User user);

	boolean isValidFaculty(String facultyNumber);
}
