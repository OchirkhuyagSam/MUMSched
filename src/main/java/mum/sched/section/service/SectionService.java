package mum.sched.section.service;

import java.util.List;

import mum.sched.entity.model.Block;
import mum.sched.entity.model.Course;
import mum.sched.entity.model.Entry;
import mum.sched.entity.model.Faculty;
import mum.sched.entity.model.Section;

public interface SectionService {

	List<Entry> getAllEntries();

	List<Block> getBlocksByEntry(Entry entry);

	List<Section> getSectionsByBlock(Block block);

	List<Course> getAllCourseFaculty();
	
	String validate(int blockID, int courseID, int facultyID);

	Course getCourseById(int courseID);

	Faculty getFacultyById(int facultyID);

	Section saveSection(Section section);

}
