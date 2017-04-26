package mum.sched.service.block;

import java.util.List;

import mum.sched.model.entity.Block;
import mum.sched.model.entity.Entry;

public interface BlockService {
	
	List<Entry> getAllEntries();

	List<Block> getAllBlocksByOrdered();

	//List<Block> getBlocksByNameAndEntryId(String blockName, int entryId);
	boolean validateBlock(String blockName, int entryId);

	Block saveBlock(Block block);
}
