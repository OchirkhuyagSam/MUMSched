package mum.sched.repository.entry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mum.sched.model.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer> {
	
	public Entry findById(Integer id);
	
	public List<Entry> findByName(String name);
}
