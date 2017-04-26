package mum.sched.service.course;

import java.util.List;

import mum.sched.model.entity.Course;

public interface CourseService {
	List<Course> getAllCourses();
	
	//List<Course> getCourseByCourseNameOrCourseNumber(String courseName, String courseNumber);
	
	boolean isValidCourse(String courseName, String courseNumber);
	
	Course saveCourse(Course course);

}
