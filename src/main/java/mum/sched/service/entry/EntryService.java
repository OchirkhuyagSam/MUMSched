package mum.sched.service.entry;

import java.util.List;

import mum.sched.model.entity.Entry;

public interface EntryService {
	
	List<Entry> getAllEntries();
	
	//List<Entry> getEntriesByName(String entryName);

	Entry saveEntry(Entry entry);

	boolean isValidateEntry(String entryName);

}
