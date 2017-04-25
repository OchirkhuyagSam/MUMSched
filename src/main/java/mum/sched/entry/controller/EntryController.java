package mum.sched.entry.controller;

import java.text.SimpleDateFormat;
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
import mum.sched.entity.model.Entry;
import mum.sched.entry.repository.EntryRepository;
import mum.sched.entry.service.EntryService;

@Controller
public class EntryController extends ControllerUtils {
	
	@Autowired
	private EntryService entryService;
	
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		if (success != null) model.addObject("success");
		if (exists != null) model.addObject("exists");

		try {
			//model.addObject("allEntryList", entryRepository.findAll());
			model.addObject("allEntryList", entryService.getAllEntries());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return model;
	}
	
	@RequestMapping(value = "/entry/create", method = RequestMethod.POST)
	public ModelAndView createEntry(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "entry_name", required = true) String entryName,
			@RequestParam(value = "begin_date", required = true) String beginDate,
			@RequestParam(value = "fpp", required = true) int numberOfFppStudents,
			@RequestParam(value = "mpp", required = true) int numberOfMppStudents) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToLogin();
		
		try {
			//List<Entry> entries = entryRepository.findByName(entryName);
			List<Entry> entries = entryService.getEntriesByName(entryName);
			if(entries.size() > 0)
				return new ModelAndView("redirect:/entry?exists");
			
			Entry entry = new Entry();
			entry.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			entry.setName(entryName);
			entry.setNumberOfFppStudents(numberOfFppStudents);
			entry.setNumberOfMppStudents(numberOfMppStudents);
			//entryRepository.save(entry);
			entryService.saveEntry(entry);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/entry?success");		
	}
}
