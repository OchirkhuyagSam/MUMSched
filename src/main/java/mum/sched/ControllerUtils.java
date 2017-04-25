package mum.sched;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public class ControllerUtils {
	
	public ModelAndView redirectToLogin() {
		return new ModelAndView("redirect:/login");
	}
	
	public ModelAndView redirectToMain() {
		return new ModelAndView("redirect:/main");
	}
	
	public ModelAndView redirectToError500() {
		return new ModelAndView("redirect:/error.jsp");
	}
	
	public ModelAndView redirectToNotFound404() {
		return new ModelAndView("redirect:/notfound.jsp");
	}
	
	public boolean checkSession(HttpSession session, UserRole role) {
		if (session.getAttribute("role") == null || session.getAttribute("loggedID") == null)
			return false;

		if (!session.getAttribute("role").equals(role))
			return false;
		return true;
	}
	
	public ModelAndView redirectToBadRequest400() {
		return new ModelAndView("redirect:/badrequest.jsp");
	}
	
	

}
