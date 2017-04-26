package mum.sched.repository.section;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
	
	public Section findById(int id);
	
	public List<Section> findByBlockId(int blockId);
}
