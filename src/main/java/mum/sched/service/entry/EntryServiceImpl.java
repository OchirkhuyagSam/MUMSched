package mum.sched.service.entry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.model.entity.Entry;
import mum.sched.repository.entry.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {
	@Autowired
	private EntryRepository entryRepository;

	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

//	@Override
//	public List<Entry> getEntriesByName(String entryName) {
//		return entryRepository.findByName(entryName);
//	}

	@Override
	public Entry saveEntry(Entry entry) {
		return entryRepository.save(entry);
	}

	@Override
	public boolean isValidateEntry(String entryName) {
		List<Entry> entries = entryRepository.findByName(entryName);
		return (entries.size() == 0);
	}
}
