package mum.sched.section.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import mum.sched.UserRole;
import mum.sched.entity.model.Block;
import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Section;
import mum.sched.entity.model.Student;
import mum.sched.section.service.SectionService;

@Controller
public class SectionController extends ControllerUtils {
	
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value = "/section/step1", method = RequestMethod.GET)
	public ModelAndView initializeEntrySelection(ModelAndView model, HttpServletRequest request, HttpSession session) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();

		try {
			model.addObject("entryList", sectionService.getAllEntries());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		model.setViewName("entrySelection");
		return model;
	}
	
	@RequestMapping(value = "/section", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "entry", required = false) Entry entry,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists,
			@RequestParam(value = "faculty-busy", required = false) String facultyNotAvailable) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		if(entry == null) return redirectToBadRequest400();
		
		if (success != null) model.addObject("success");
		if (exists != null) model.addObject("exists");
		if (facultyNotAvailable != null) model.addObject("faculty-busy");
		
		try {
			model.addObject("blockList", sectionService.getBlocksByEntry(entry));
			model.addObject("entry", entry);
			model.addObject("courseFacultyList", sectionService.getAllCourseFaculty());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return model;
	}
	
	@RequestMapping(value = "/section/create", method = RequestMethod.POST)
	public ModelAndView createBlock(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "block", required = true) Block block,
			@RequestParam(value = "course_faculty", required = true) String courseFaculty,
			@RequestParam(value = "location", required = true) String location,
			@RequestParam(value = "max_student", required = true) int maxStudent) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		int courseID = Integer.parseInt(courseFaculty.substring(0, courseFaculty.indexOf('/')));
		int facultyID = Integer.parseInt(courseFaculty.substring(courseFaculty.indexOf('/') + 1));
		
		try {
			String result = sectionService.validate(block.getId(), courseID, facultyID);
			if(result.equals("exists"))
				return new ModelAndView("redirect:/section?exists&entry=" + block.getEntry().getId());
			if(result.equals("faculty-busy"))
				return new ModelAndView("redirect:/section?faculty-busy&entry=" + block.getEntry().getId());

			Section section = new Section();
			section.setBlock(block);
			section.setCourse(sectionService.getCourseById(courseID));
			section.setFaculty(sectionService.getFacultyById(facultyID));
			section.setLocation(location);
			section.setNumberOfStudent(maxStudent);
			section.setStudents(new ArrayList<Student>());
			sectionService.saveSection(section);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/section?success&entry=" + block.getEntry().getId());
	}
}
