package mum.sched.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "section")
public class Section {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="section_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="block_fk_id")
	private Block block;
	
	private String location;
	private int numberOfStudent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_fk_id")
	private Course course;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="faculty_fk_id")
	private Faculty faculty;
	
	@ManyToMany
	@JoinTable(name="student_section", joinColumns=@JoinColumn(name="secion_id"), inverseJoinColumns=@JoinColumn(name="student_id"))
	private List<Student> students = new ArrayList<Student>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumberOfStudent() {
		return numberOfStudent;
	}

	public void setNumberOfStudent(int numberOfStudent) {
		this.numberOfStudent = numberOfStudent;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public int getAvailableSeat() {
		return numberOfStudent - students.size();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Section other = (Section) obj;
		if (id != other.id) return false;
		return true;
	}
}
