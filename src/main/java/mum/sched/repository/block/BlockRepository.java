package mum.sched.repository.block;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mum.sched.model.entity.Block;

public interface BlockRepository extends JpaRepository<Block, Integer> {

	public Block findById(Integer id);
	
	@Query("SELECT b FROM Block b INNER JOIN b.entry e ORDER BY e.beginDate, e.beginDate")
	public List<Block> findAllOrdered();

	public List<Block> findByNameAndEntryId(String name, int entryId);
	
	public List<Block> findByEntryIdOrderByBeginDate(int entryId);
}
