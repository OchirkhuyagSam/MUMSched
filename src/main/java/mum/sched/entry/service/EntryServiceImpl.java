package mum.sched.entry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.entity.model.Entry;
import mum.sched.entry.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {
	@Autowired
	private EntryRepository entryRepository;

	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

	@Override
	public List<Entry> getEntriesByName(String entryName) {
		return entryRepository.findByName(entryName);
	}

	@Override
	public Entry saveEntry(Entry entry) {
		return entryRepository.save(entry);
	}
}
