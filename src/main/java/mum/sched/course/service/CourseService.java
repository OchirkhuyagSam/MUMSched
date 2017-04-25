package mum.sched.course.service;

import java.util.List;

import mum.sched.entity.model.Course;

public interface CourseService {
	List<Course> getAllCourses();
	
	List<Course> getCourseByCourseNameOrCourseNumber(String courseName, String courseNumber);
	
	Course saveCourse(Course course);

}
