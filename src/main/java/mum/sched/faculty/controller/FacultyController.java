package mum.sched.faculty.controller;

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

import mum.sched.ControllerUtils;
import mum.sched.UserRole;
import mum.sched.course.repository.CourseRepository;
import mum.sched.course.service.CourseService;
import mum.sched.entity.model.Course;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.User;
import mum.sched.faculty.repository.FacultyRepository;
import mum.sched.faculty.service.FacultyService;
import mum.sched.user.repository.UserRepository;
import mum.sched.user.service.UserService;

@Controller
public class FacultyController extends ControllerUtils {

//	@Autowired
//	private FacultyRepository facultyRepository;
	
	@Autowired
	private FacultyService facultyService;
	
//	@Autowired
//	private CourseRepository courseRepository;
	
//	@Autowired
//	private UserRepository userRepository;
	
	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {

		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		if (success != null)
			model.addObject("success");
		if (exists != null)
			model.addObject("exists");

		try {
			//model.addObject("allFacultyList", facultyRepository.findAll());
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
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		try {
			//List<Faculty> faculties = facultyRepository.findByFacultyNumber(facultyID);
			List<Faculty> faculties = facultyService.getFacultyByFacultyNumber(facultyID);
			if(faculties.size() > 0)
				return new ModelAndView("redirect:/faculty?exists");
			
			Faculty faculty = new Faculty();
			faculty.setFirstName(firstName);
			faculty.setLastName(lastName);
			faculty.setFacultyNumber(facultyID);
			faculty.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			//faculty = facultyRepository.save(faculty);
			faculty = facultyService.saveFaculty(faculty);
			
			User user = new User();
			user.setUsername(facultyID);
			user.setPassword("pass1");
			user.setPersonId(faculty.getId());
			user.setRole(mum.sched.UserRole.FACULTY);
//			userRepository.save(user);
			facultyService.saveUser(user);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/faculty?success");		
	}
	
	@RequestMapping(value = "/faculty/course", method = RequestMethod.GET)
	public ModelAndView initializeProfile(ModelAndView model, HttpServletRequest request, HttpSession session) {
		
		if(!checkSession(session, UserRole.FACULTY)) return redirectToLogin();
		
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
		
		if(!checkSession(session, UserRole.FACULTY)) return redirectToLogin();
		
		try {
			//Faculty currentFaculty = facultyRepository.findById(Integer.parseInt(session.getAttribute("loggedID").toString()));
			Faculty currentFaculty = facultyService.getFacultyById(Integer.parseInt(session.getAttribute("loggedID").toString()));
			currentFaculty.addCourse(course);
			//facultyRepository.save(currentFaculty);
			facultyService.saveFaculty(currentFaculty);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/faculty/course");		
	}
}
