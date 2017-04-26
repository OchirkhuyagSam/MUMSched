package mum.sched.service.section;

import java.util.List;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Course;
import mum.sched.model.entity.Entry;
import mum.sched.model.entity.Faculty;
import mum.sched.model.entity.Section;

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
