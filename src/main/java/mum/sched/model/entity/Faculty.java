package mum.sched.model.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty extends Person {
	
	@Column(name = "faculty_number")
	private String facultyNumber;
	
	@ManyToMany
	@JoinTable(name="faculty_course", joinColumns=@JoinColumn(name="faculty_id"), inverseJoinColumns=@JoinColumn(name="course_id"))
	private List<Course> courses = new ArrayList<Course>();

	public List<Course> getCourseList() {
		return courses;
	}

	public void setCourseList(List<Course> courses) {
		this.courses = courses;
	}

	public String getFacultyNumber() {
		return facultyNumber;
	}
	
	public void setFacultyNumber(String facultyNumber) {
		this.facultyNumber = facultyNumber;
	}
	
	public void addCourse(Course course) {
		courses.add(course);
	}
}
