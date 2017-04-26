package mum.sched.service.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Entry;
import mum.sched.model.entity.Section;
import mum.sched.model.entity.Student;
import mum.sched.model.entity.User;
import mum.sched.repository.entry.EntryRepository;
import mum.sched.repository.student.StudentRepository;
import mum.sched.repository.user.UserRepository;
import mum.sched.utils.StudentTrack;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

	@Override
	public List<User> getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public Student getStudentByID(int studentID) {
		return studentRepository.findById(studentID);
	}

	@Override
	public String validateNewSection(Student student, Section section) {
		//If this section is already registered/Ene section-iig al hediin songochihson bol
		if(student.getSections().stream().anyMatch(s -> s.getId() == section.getId()))
			return "exists";
		
		//If the student has already chosen section in the block that contains this section
		//Ene section-ii bgaa block dahi hicheeliig ali hediin songochihson bol
		if(student.getSections().stream().anyMatch(s -> s.getBlock().equals(section.getBlock())))
			return "chosen-in-block";
		
		//In the entry, if there is not the section chosen
		if(student.getEntry().getId() != section.getBlock().getEntry().getId())
			return "not-found-in-entry";
		
		//If first block && mpp student && select not MPP
		if((isFirstBlockInEntry(section.getBlock())
				&& student.getType() == StudentTrack.MPP
				&& !section.getCourse().getCourseNumber().equals("CS401"))
				|| (isSecondBlockInEntry(section.getBlock())
				&& student.getType() == StudentTrack.FPP
				&& !section.getCourse().getCourseNumber().equals("CS401")))
			return "select-mpp";
		
		//If first block && fpp student && select not FPP
		if(isFirstBlockInEntry(section.getBlock())
				&& student.getType() == StudentTrack.FPP
				&& !section.getCourse().getCourseNumber().equals("CS390"))
			return "select-fpp";
		
		//If last block && mpp student
		if(isLastBlockInEntry(section.getBlock())
				&& student.getType() == StudentTrack.MPP)
			return "no-need";
		return "valid";
	}
	
	private boolean isFirstBlockInEntry(Block block) {
		return block.getEntry().getBlocks().get(0).getId() == block.getId();
	}
	
	private boolean isSecondBlockInEntry(Block block) {
		if(block.getEntry().getBlocks().size() > 1)
			return block.getEntry().getBlocks().get(1).equals(block);
		return false;
	}
	
	private boolean isLastBlockInEntry(Block block) {
		if(block.getEntry().getBlocks().size() > 2)
			return block.getEntry().getBlocks().get(block.getEntry().getBlocks().size() - 1).equals(block);
		return false;
	}
}
