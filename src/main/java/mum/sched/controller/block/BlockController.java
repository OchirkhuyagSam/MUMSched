package mum.sched.controller.block;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Entry;
import mum.sched.repository.block.BlockRepository;
import mum.sched.repository.entry.EntryRepository;
import mum.sched.service.block.BlockService;
import mum.sched.utils.ControllerUtils;
import mum.sched.utils.UserRole;

@Controller
public class BlockController extends ControllerUtils {
	
	@Autowired
	private BlockService blockService;
	
	@RequestMapping(value = "/block", method = RequestMethod.GET)
	public ModelAndView initialize(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "success", required = false) String success,
			@RequestParam(value = "exists", required = false) String exists) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		if (success != null) model.addObject("success");
		if (exists != null) model.addObject("exists");

		try {
			model.addObject("allEntryList", blockService.getAllEntries());
			model.addObject("allBlockList", blockService.getAllBlocksByOrdered());
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return model;
	}
	
	@RequestMapping(value = "/block/create", method = RequestMethod.POST)
	public ModelAndView createBlock(ModelAndView model, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "entry", required = true) Entry entry,
			@RequestParam(value = "block_name", required = true) String blockName,
			@RequestParam(value = "begin_date", required = true) String beginDate,
			@RequestParam(value = "end_date", required = true) String endDate) {
		
		if(!checkSession(session, UserRole.ADMIN)) return redirectToMain();
		
		try {
			//List<Block> blocks = blockService.getBlocksByNameAndEntryId(blockName, entry.getId());
			if(!blockService.validateBlock(blockName, entry.getId()))
				return new ModelAndView("redirect:/block?exists");
					
			//if(blocks.size() > 0)
			//	return new ModelAndView("redirect:/block?exists");
			
			Block block = new Block();
			block.setBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
			block.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
			block.setName(blockName);
			block.setEntry(entry);
			blockService.saveBlock(block);
		} catch (Exception ex) {
			ex.printStackTrace();
			return redirectToError500();
		}
		return new ModelAndView("redirect:/block?success");		
	}
}
