package mum.sched.controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.model.entity.Administrator;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.Student;
import mum.sched.repository.admin.AdminRepository;
import mum.sched.repository.faculty.FacultyRepository;
import mum.sched.repository.student.StudentRepository;
import mum.sched.utils.ControllerUtils;
import mum.sched.utils.UserRole;

@Controller
public class MainController extends ControllerUtils {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private FacultyRepository facultyRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return redirectToMain();
    }
	
	@RequestMapping(value = "/**", method = RequestMethod.GET)
    public ModelAndView catchUnknownURLs() {
        return redirectToNotFound404();
    }

	private ModelAndView showAdminPage(ModelAndView model, HttpServletRequest request, HttpSession session) {
		
		try {
			Administrator admin = adminRepository.findById((int)(session.getAttribute("loggedID")));
			session.setAttribute("user", admin);
			model.addObject("name", admin.getFirstName() + " " + admin.getLastName());			
		} catch(Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("adminMainPage");
		return model;
	}

	private ModelAndView showFacultyPage(ModelAndView model, HttpServletRequest request, HttpSession session) {
		
		try {
			Faculty faculty = facultyRepository.findById((int)(session.getAttribute("loggedID")));
			session.setAttribute("user", faculty);
			model.addObject("name", faculty.getFirstName() + " " + faculty.getLastName());
		} catch(Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("facultyMainPage");
		return model;
	}
	
	private ModelAndView showStudentPage(ModelAndView model, HttpServletRequest request, HttpSession session) {

		try {
			Student student = studentRepository.findById((int)(session.getAttribute("loggedID")));
			session.setAttribute("user", student);
			model.addObject("name", student.getFirstName() + " " + student.getLastName());			
		} catch(Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("studentMainPage");
		return model;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(ModelAndView model, HttpServletRequest request, HttpSession session) {		
		
		if(checkSession(session, UserRole.ADMIN))
			return showAdminPage(model, request, session);
		if(checkSession(session, UserRole.FACULTY))
			return showFacultyPage(model, request, session);
		if(checkSession(session, UserRole.STUDENT))
			return showStudentPage(model, request, session);
		return redirectToLogin();
	}
}
