package mum.sched.entity.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name = "course_name")
	private String courseName;
    
	@Column(name = "course_number")
	private String courseNumber;
    
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="pre_course_id")
	private Course prerequisites;
	
	@OneToMany(mappedBy="prerequisites")
	private List<Course> postCourses = new ArrayList<Course>();
    
	@ManyToMany
	@JoinTable(name="faculty_course", joinColumns=@JoinColumn(name="course_id"), inverseJoinColumns=@JoinColumn(name="faculty_id"))
    private List<Faculty> faculties = new ArrayList<Faculty>();

    public List<Course> getPostCourses() {
		return postCourses;
	}

	public void setPostCourses(List<Course> postCourses) {
		this.postCourses = postCourses;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public Course getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Course prerequisites) {
		this.prerequisites = prerequisites;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}
}
