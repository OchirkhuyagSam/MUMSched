package mum.sched.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.course.repository.CourseRepository;
import mum.sched.entity.model.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public List<Course> getCourseByCourseNameOrCourseNumber(String courseName, String courseNumber) {
		return courseRepository.findByCourseNameOrCourseNumber(courseName, courseNumber);
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

}
