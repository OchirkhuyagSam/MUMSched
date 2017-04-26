package mum.sched.service.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.model.entity.Course;
import mum.sched.repository.course.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

//	@Override
//	public List<Course> getCourseByCourseNameOrCourseNumber(String courseName, String courseNumber) {
//		return courseRepository.findByCourseNameOrCourseNumber(courseName, courseNumber);
//	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public boolean isValidCourse(String courseName, String courseNumber) {
		List<Course> courses = courseRepository.findByCourseNameOrCourseNumber(courseName, courseNumber);
		return (courses.size() == 0);
	}

}
