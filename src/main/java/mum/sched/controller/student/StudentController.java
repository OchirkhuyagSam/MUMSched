package mum.sched.controller.student;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import mum.sched.model.entity.Entry;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.Section;
import mum.sched.model.entity.Student;
import mum.sched.model.entity.User;
import mum.sched.repository.entry.EntryRepository;
import mum.sched.repository.student.StudentRepository;
import mum.sched.repository.user.UserRepository;
import mum.sched.service.student.StudentService;
import mum.sched.utils.ControllerUtils;
import mum.sched.utils.StudentTrack;
import mum.sched.utils.UserRole;

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

			user.setRole(mum.sched.utils.UserRole.STUDENT);
			//userRepository.save(user);
			studentService.saveUser(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/student?success");		
	}
	
	@RequestMapping(value = "/student/section", method = RequestMethod.GET)
	public ModelAndView viewSections(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists,
			@RequestParam(value = "chosen-in-block", required = false) String chosenInBlock,
			@RequestParam(value = "not-found-in-entry", required = false) String notFoundInEntry,
			@RequestParam(value = "select-mpp", required = false) String selectMPP,
			@RequestParam(value = "select-fpp", required = false) String selectFPP,
			@RequestParam(value = "no-need", required = false) String noNeed) {
		
		if(!checkSession(session, UserRole.STUDENT)) return redirectToMain();
		
		if (success != null)
			model.addObject("success");
		if (exists != null)
			model.addObject("exists");
		if (chosenInBlock != null)
			model.addObject("chosen-in-block");
		if (notFoundInEntry != null)
			model.addObject("not-found-in-entry");
		if (selectMPP != null)
			model.addObject("select-mpp");
		if (selectFPP != null)
			model.addObject("select-fpp");
		if (noNeed != null)
			model.addObject("no-need");
		
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
	
	@RequestMapping(value = "/student/section/add", method = RequestMethod.GET)
	public ModelAndView addSection(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "section", required = false) Section section) {
		
		if(!checkSession(session, UserRole.STUDENT)) return redirectToMain();
		
		try {
			Student currentStudent = studentService.getStudentByID(Integer.parseInt(session.getAttribute("loggedID").toString()));
			
			String result;
			if(!(result = studentService.validateNewSection(currentStudent, section)).equals("valid"))
				return new ModelAndView("redirect:/student/section?" + result);
			
			currentStudent.addSection(section);
			studentService.saveStudent(currentStudent);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/student/section?success");
	}
}
