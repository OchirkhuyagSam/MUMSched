package mum.sched.service.block;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Entry;
import mum.sched.repository.block.BlockRepository;
import mum.sched.repository.entry.EntryRepository;

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

//	@Override
//	public List<Block> getBlocksByNameAndEntryId(String blockName, int entryId) {
//		return blockRepository.findByNameAndEntryId(blockName, entryId);
//	}

	@Override
	public Block saveBlock(Block block) {
		return blockRepository.save(block);
	}

	@Override
	public boolean validateBlock(String blockName, int entryId) {
		List<Block> blocks = blockRepository.findByNameAndEntryId(blockName, entryId);
		return blocks.size() == 0;
	}
}
