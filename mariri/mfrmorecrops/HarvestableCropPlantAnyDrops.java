package mariri.mfrmorecrops;

import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableCropPlant;

public class HarvestableCropPlantAnyDrops extends HarvestableCropPlant{
	private int targetMeta;
	
	HarvestableCropPlantAnyDrops(int sourceId, int targetMeta){
		super(sourceId, targetMeta);
		this.targetMeta = targetMeta;
	}
	
	@Override
	public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z){
		return Utils.getAnyDrops(world, rand, harvesterSettings, x, y, z, getPlantId());
	}
	
	@Override
	public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z) == targetMeta;
	}

}
