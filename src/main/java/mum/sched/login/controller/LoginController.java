package mum.sched.login.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.ControllerUtils;
import mum.sched.UserRole;
import mum.sched.actor.repository.AdminRepository;
import mum.sched.entity.model.Administrator;
import mum.sched.entity.model.User;
import mum.sched.login.service.LoginService;

@Controller
public class LoginController extends ControllerUtils {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		session.invalidate();

		if (error != null)
			model.addObject("error", "Invalid username and password!");
		if (logout != null)
			model.addObject("msg", "You've been logged out successfully.");

		model.setViewName("login");
		return model;
	}

	@PostMapping("/login")
	public ModelAndView login(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {

		List<User> userList;
		try {
			userList = loginService.getUsersByUsernameAndPassword(username, password);

			if (userList.size() != 1) {
				model.addObject("error", "Invalid username and password!");
				model.setViewName("login");
				return model;
			}
			session.setAttribute("role", userList.get(0).getRole());
			session.setAttribute("loggedID", userList.get(0).getPersonId());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/main");
	}

	//JUST inserting test data. It will be deleted!!!!!!!!!!!
//	private void createAdminTestData() {
//		Administrator admin = new Administrator();
//		admin.setFirstName("Bill");
//		admin.setLastName("Gates");
//		admin.setEmployeeID("E20170001");
//		admin.setDateOfBirth(new Date(57, 10, 25));
////
//		adminRepository.save(admin);
//		
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword("pass1");
//		user.setRole(UserRole.ADMIN);
//		user.setPersonId(1);
//		userRepository.save(user);
//	}
}
