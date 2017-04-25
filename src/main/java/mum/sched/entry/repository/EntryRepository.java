package mum.sched.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import mum.sched.entity.model.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
	
	public Entry findById(Integer id);
	
	public List<Entry> findByName(String name);
}
