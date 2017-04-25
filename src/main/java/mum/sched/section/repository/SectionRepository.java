package mum.sched.section.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.entity.model.Block;
import mum.sched.entity.model.Section;

public interface SectionRepository extends JpaRepository<Section, Integer> {
	
	public Section findById(int id);
	
	public List<Section> findByBlockId(int blockId);
}
