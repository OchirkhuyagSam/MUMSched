package mum.sched.entry.service;

import java.util.List;

import mum.sched.entity.model.Entry;

public interface EntryService {
	
	List<Entry> getAllEntries();
	
	List<Entry> getEntriesByName(String entryName);

	Entry saveEntry(Entry entry);

}
