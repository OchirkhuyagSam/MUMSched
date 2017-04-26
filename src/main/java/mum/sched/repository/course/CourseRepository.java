package mum.sched.repository.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mum.sched.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	public Course findById(Integer id);
	
	public List<Course> findByCourseNameOrCourseNumber(String courseName, String courseNumber);
	
	@Query("SELECT c FROM Course c WHERE c NOT IN (SELECT c2 FROM Faculty f JOIN f.courses c2 WHERE f.id=:facultyID)")
	public List<Course> findCoursesNotInFaculty(@Param("facultyID") int facultyID);

	//@Query("SELECT c FROM Course c JOIN c.faculties f")
	public List<Course> findByFacultiesIsNotNull();
}
