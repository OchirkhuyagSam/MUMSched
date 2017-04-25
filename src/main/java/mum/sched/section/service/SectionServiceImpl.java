package mum.sched.section.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.block.repository.BlockRepository;
import mum.sched.course.repository.CourseRepository;
import mum.sched.entity.model.Block;
import mum.sched.entity.model.Course;
import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.Section;
import mum.sched.entry.repository.EntryRepository;
import mum.sched.faculty.repository.FacultyRepository;
import mum.sched.section.repository.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private FacultyRepository facultyRepository; 
	
	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

	@Override
	public List<Block> getBlocksByEntry(Entry entry) {
		return blockRepository.findByEntryIdOrderByBeginDate(entry.getId());
	}

	@Override
	public List<Section> getSectionsByBlock(Block block) {
		return sectionRepository.findByBlockId(block.getId());
	}

	@Override
	public List<Course> getAllCourseFaculty() {
		// TODO Auto-generated method stub
		return courseRepository.findByFacultiesIsNotNull();
	}

	@Override
	public String validate(int blockID, int courseID, int facultyID) {
		Block block = blockRepository.findById(blockID);
		
		List<Section> sectionList = block.getSections()
				.stream()
				.filter(s -> s.getFaculty().getId() == facultyID)
				.collect(Collectors.toList());
		
		if(sectionList.size() == 0)
			return "valid";
		
		if(sectionList.stream().filter(s -> s.getCourse().getId() == courseID).count() > 0)
			return "exists";
		return "faculty-busy";
	}

	@Override
	public Course getCourseById(int courseID) {
		return courseRepository.findById(courseID);
	}

	@Override
	public Faculty getFacultyById(int facultyID) {
		return facultyRepository.findById(facultyID);
	}

	@Override
	public Section saveSection(Section section) {
		return sectionRepository.save(section);
	}
	
}
