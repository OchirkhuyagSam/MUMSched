package mum.sched.block.service;

import java.util.List;

import mum.sched.entity.model.Block;
import mum.sched.entity.model.Entry;

public interface BlockService {
	
	List<Entry> getAllEntries();

	List<Block> getAllBlocksByOrdered();

	List<Block> getBlocksByNameAndEntryId(String blockName, int entryId);

	Block saveBlock(Block block);
}
