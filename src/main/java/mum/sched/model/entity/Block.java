package mum.sched.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "block")
public class Block implements Comparable<Block> {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="block_id")
	private int id;
	private String name;
	private Date beginDate;
	private Date endDate;
	
	@OneToMany(mappedBy="block")
	private List<Section> sections;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entry_fk_id")
	private Entry entry;

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public String getDuration(String dateFormat) {
		return (new SimpleDateFormat(dateFormat).format(beginDate)) +
				" - " + (new SimpleDateFormat(dateFormat).format(endDate));
	}

	@Override
	public int compareTo(Block block) {
		return getBeginDate().compareTo(block.getBeginDate());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Block other = (Block) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
