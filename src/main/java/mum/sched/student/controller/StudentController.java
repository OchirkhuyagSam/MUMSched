package mum.sched.student.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.ControllerUtils;
import mum.sched.StudentTrack;
import mum.sched.UserRole;
import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.Student;
import mum.sched.entity.model.User;
import mum.sched.entry.repository.EntryRepository;
import mum.sched.student.repository.StudentRepository;
import mum.sched.student.service.StudentService;
import mum.sched.user.repository.UserRepository;

@Controller
public class StudentController extends ControllerUtils {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ModelAndView initializeNewStudent(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {

		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		if (success != null)
			model.addObject("success");
		if (exists != null)
			model.addObject("exists");

		try {
			model.addObject("allStudentList", studentService.getAllStudents());
			model.addObject("allEntryList", studentService.getAllEntries());
		} catch (Exception ex) {
			return redirectToError500();
		}
		return model;
	}
	
	@RequestMapping(value = "/student/create", method = RequestMethod.POST)
	public ModelAndView createStudent(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "first_name", required = true) String firstName,
			@RequestParam(value = "last_name", required = true) String lastName,
			@RequestParam(value = "student_id", required = true) String studentID,
			@RequestParam(value = "birthday", required = true) String birthday,
			@RequestParam(value = "student_type", required = true) String type,
			@RequestParam(value = "entry", required = true) Entry entry) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		try {
			List<User> users = studentService.getUserByUsername(studentID);
			if(users.size() > 0)
				return new ModelAndView("redirect:/student?exists");
			
			Student student = new Student();
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setStudentNumber(studentID);
			student.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
			student.setType(StudentTrack.valueOf(type.toUpperCase()));
			student.setEntry(entry);
			student = studentService.saveStudent(student);
			
			User user = new User();
			user.setUsername(studentID);
			user.setPassword("pass1");
			user.setPersonId(student.getId());

			user.setRole(mum.sched.UserRole.STUDENT);
			//userRepository.save(user);
			studentService.saveUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/student?success");		
	}
	
	@RequestMapping(value = "/student/addcourse", method = RequestMethod.GET)
	public ModelAndView addCourse(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {
		
		if(!checkSession(session, UserRole.STUDENT)) return redirectToMain();
		
		try {
			Student currentStudent = studentService.getStudentByID(Integer.parseInt(session.getAttribute("loggedID").toString()));
			model.addObject("entry", currentStudent.getEntry());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("registerForSection");
		return model;
	}
}
