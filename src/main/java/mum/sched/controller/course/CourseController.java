package mum.sched.controller.course;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.model.entity.Course;
import mum.sched.repository.course.CourseRepository;
import mum.sched.service.course.CourseService;
import mum.sched.utils.ControllerUtils;
import mum.sched.utils.UserRole;

@Controller
public class CourseController extends ControllerUtils {
	
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		if (success != null) model.addObject("success");
		if (exists != null) model.addObject("exists");

		try {
			model.addObject("allCourseList", courseService.getAllCourses());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return model;
	}

	@RequestMapping(value = "/course/create", method = RequestMethod.POST)
	public ModelAndView createCourse(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "course_name", required = true) String courseName,
			@RequestParam(value = "course_number", required = true) String courseNumber,
			@RequestParam(value = "pre_course", required = true) Course preCourse) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		try {
			//List<Course> courses = courseService.getCourseByCourseNameOrCourseNumber(courseName, courseNumber);
			//if(courses.size() > 0)
			if(!courseService.isValidCourse(courseName, courseNumber))
				return new ModelAndView("redirect:/course?exists");
			
			Course course = new Course();
			course.setCourseName(courseName);
			course.setCourseNumber(courseNumber);
			course.setPrerequisites(preCourse);
			
			courseService.saveCourse(course);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/course?success");
	}
}
