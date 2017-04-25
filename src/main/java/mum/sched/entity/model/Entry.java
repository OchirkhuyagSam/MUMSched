package mum.sched.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "entry")
public class Entry {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="entry_id")
	private int id;
	private String name;
	
	@Column(name = "begin_date")
	private Date beginDate;
	private int numberOfMppStudents;
	private int numberOfFppStudents;
	
	@OneToMany(mappedBy="entry")
	private List<Student> students;
	
	@OneToMany(mappedBy="entry")
	private List<Block> blocks = new ArrayList<Block>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfMppStudents() {
		return numberOfMppStudents;
	}
	public void setNumberOfMppStudents(int numberOfMppStudents) {
		this.numberOfMppStudents = numberOfMppStudents;
	}
	public int getNumberOfFppStudents() {
		return numberOfFppStudents;
	}
	public void setNumberOfFppStudents(int numberOfFppStudents) {
		this.numberOfFppStudents = numberOfFppStudents;
	}
	public List<Block> getBlocks() {
		return blocks;
	}
	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}
}
