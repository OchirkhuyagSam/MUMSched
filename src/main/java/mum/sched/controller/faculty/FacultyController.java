package mum.sched.controller.faculty;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.model.entity.Course;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.User;
import mum.sched.repository.course.CourseRepository;
import mum.sched.repository.faculty.FacultyRepository;
import mum.sched.repository.user.UserRepository;
import mum.sched.service.course.CourseService;
import mum.sched.service.faculty.FacultyService;
import mum.sched.service.user.UserService;
import mum.sched.utils.ControllerUtils;
import mum.sched.utils.UserRole;

@Controller
public class FacultyController extends ControllerUtils {

	@Autowired
	private FacultyService facultyService;
	
	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {

		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		if (success != null)
			model.addObject("success");
		if (exists != null)
			model.addObject("exists");

		try {
			model.addObject("allFacultyList", facultyService.getAllFaculties());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return model;
	}
	
	@RequestMapping(value = "/faculty/create", method = RequestMethod.POST)
	public ModelAndView createFaculty(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "first_name", required = true) String firstName,
			@RequestParam(value = "last_name", required = true) String lastName,
			@RequestParam(value = "faculty_id", required = true) String facultyID,
			@RequestParam(value = "birthday", required = true) String birthday) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		try {
//			List<Faculty> faculties = facultyService.getFacultyByFacultyNumber(facultyID);
//			if(faculties.size() > 0)
//				return new ModelAndView("redirect:/faculty?exists");
			
			if(!facultyService.isValidFaculty(facultyID))
				return new ModelAndView("redirect:/faculty?exists");
			
			Faculty faculty = new Faculty();
			faculty.setFirstName(firstName);
			faculty.setLastName(lastName);
			faculty.setFacultyNumber(facultyID);
			faculty.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			faculty = facultyService.saveFaculty(faculty);
			
			User user = new User();
			user.setUsername(facultyID);
			user.setPassword("pass1");
			user.setPersonId(faculty.getId());
			user.setRole(mum.sched.utils.UserRole.FACULTY);
			facultyService.saveUser(user);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/faculty?success");		
	}
	
	@RequestMapping(value = "/faculty/course", method = RequestMethod.GET)
	public ModelAndView initializeProfile(ModelAndView model, HttpServletRequest request, HttpSession session) {
		
		if(!checkSession(session, UserRole.FACULTY)) return redirectToMain();
		
		try {
			Faculty currentFaculty = facultyService.getFacultyById(Integer.parseInt(session.getAttribute("loggedID").toString()));
			
			model.addObject("allCoursesOfFaculty", currentFaculty.getCourseList());
			model.addObject("allOtherCourses", facultyService.getCoursesNotInFaculty(currentFaculty.getId()));
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("fcourse");
		return model;
	}
	
	@RequestMapping(value = "/faculty/course/add", method = RequestMethod.POST)
	public ModelAndView addCourse(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "course", required = true) Course course) {
		
		if(!checkSession(session, UserRole.FACULTY)) return redirectToMain();
		
		try {
			Faculty currentFaculty = facultyService.getFacultyById(Integer.parseInt(session.getAttribute("loggedID").toString()));
			currentFaculty.addCourse(course);
			facultyService.saveFaculty(currentFaculty);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/faculty/course");		
	}
}
