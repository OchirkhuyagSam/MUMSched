package mum.sched.block.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.block.repository.BlockRepository;
import mum.sched.entity.model.Block;
import mum.sched.entity.model.Entry;
import mum.sched.entry.repository.EntryRepository;

@Service
public class BlockServiceImpl implements BlockService {
	@Autowired
	private BlockRepository blockRepository;
	
	@Autowired
	private EntryRepository entryRepository;

	@Override
	public List<Entry> getAllEntries() {
		return entryRepository.findAll();
	}

	@Override
	public List<Block> getAllBlocksByOrdered() {
		return blockRepository.findAllOrdered();
	}

	@Override
	public List<Block> getBlocksByNameAndEntryId(String blockName, int entryId) {
		return blockRepository.findByNameAndEntryId(blockName, entryId);
	}

	@Override
	public Block saveBlock(Block block) {
		return blockRepository.save(block);
	}
}
