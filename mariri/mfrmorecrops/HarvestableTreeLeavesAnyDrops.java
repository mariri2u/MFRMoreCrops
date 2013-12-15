package mariri.mfrmorecrops;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableTreeLeaves;

public class HarvestableTreeLeavesAnyDrops extends HarvestableTreeLeaves{
	
	HarvestableTreeLeavesAnyDrops(int sourceId){
		super(sourceId);
	}
	
	@Override
	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z){
		return Utils.getAnyDrops(world, rand, harvesterSettings, x, y, z, getPlantId());
	}

}
