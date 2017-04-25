package mum.sched.entity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mum.sched.StudentTrack;

@Entity
@Table(name = "student")
public class Student extends Person {
	
	@Column(name = "student_number")
	private String studentNumber;
	
	@Column(name = "student_type")
	private StudentTrack type;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entry_fk_id")
	private Entry entry;
	
	@ManyToMany
	@JoinTable(name="student_section", joinColumns=@JoinColumn(name="student_id"), inverseJoinColumns=@JoinColumn(name="secion_id"))
	private List<Section> sections = new ArrayList<Section>();

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public StudentTrack getType() {
		return type;
	}

	public void setType(StudentTrack type) {
		this.type = type;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}
