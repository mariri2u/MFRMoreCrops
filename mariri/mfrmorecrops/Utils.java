package mariri.mfrmorecrops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Utils {

	public static List<ItemStack> getAnyDrops(World world, Random rand, Map<String, Boolean> harvesterSetting, int x, int y, int z, int itemId){
		List<ItemStack> drops;
		Block block = Block.blocksList[itemId];
		int metadata = world.getBlockMetadata(x, y, z);
		drops = block.getBlockDropped(world, x, y, z, metadata, 0);
		block.dropBlockAsItemWithChance(world, x, y, z, metadata, 0, 0);
//		for(Entity entity : (List<Entity>)world.getLoadedEntityList()){
//			if(
//					(x - 3 <= entity.posX ||  entity.posX <= x + 3) &&
//					(y - 3 <= entity.posY ||  entity.posY <= y + 3) &&
//					(z - 9 <= entity.posZ ||  entity.posZ <= z + 3) &&
//					entity instanceof EntityItem
//			){
//				EntityItem item = (EntityItem)entity;
//				drops.add(item.getEntityItem());
//				item.setDead();
//			}
//		}
		drops.addAll(pickUpItemStack(world, x, y, z));
		return drops;
	}
	
//	public static List<ItemStack> getShearsDrop(World world, Random rand, Map<String, Boolean> harvesterSetting, int x, int y, int z, int itemId){
//		List<ItemStack> drops = new ArrayList<ItemStack>();
//		Block block = Block.blocksList[itemId];
//		int metadata = world.getBlockMetadata(x, y, z);
//		EntityPlayer dummy = new EntityPlayer(world, "mmcdummy") {
//			@Override
//			public void sendChatToPlayer(ChatMessageComponent chatmessagecomponent) {
//				// TODO 自動生成されたメソッド・スタブ
//				
//			}
//			
//			@Override
//			public ChunkCoordinates getPlayerCoordinates() {
//				// TODO 自動生成されたメソッド・スタブ
//				return null;
//			}
//			
//			@Override
//			public boolean canCommandSenderUseCommand(int i, String s) {
//				// TODO 自動生成されたメソッド・スタブ
//				return false;
//			}
//		};
//		dummy.setCurrentItemOrArmor(0, new ItemStack(Item.shears));
//		block.onBlockActivated(world, x, y, z, dummy, 0, 0, 0, 0);
//		drops.addAll(pickUpItemStack(world, x, y, z));
//		return drops;
//		
//	}
	
	public static List<ItemStack> pickUpItemStack(World world, int posX, int posY, int posZ){
		List<ItemStack> itemStack = new ArrayList<ItemStack>();

//        for(int x = (posX - 3 >> 4); x <= (posX + 3 >> 4); x++)
//        {
//            for(int z = (posZ - 3 >> 4); z <= (posZ + 3 >> 4); z++)
//            {
//                Chunk chunk = world.getChunkFromChunkCoords(x, z);
//                if (chunk != null)
//                {
//                    for(Object obj : chunk.entityLists)
//                    {
//                    	if(obj instanceof EntityItem){
//	                        EntityItem entity = (EntityItem)obj;
//                            if (entity.posX >= posX - 3 && entity.posY >= posY - 7 && entity.posZ >= posZ - 3 &&
//                                entity.posX <= posX + 3 && entity.posY <= posY + 1 && entity.posZ <= posZ + 3)
//                            {
//                            	itemStack.add(entity.getEntityItem());
//                            	entity.setDead();
//                            }
//                    	}
//                    }
//                }
//            }
//        }
		
		
			List entityList = world.loadedEntityList;
			for(Object obj : entityList){
				if(obj instanceof EntityItem){
					EntityItem entity = (EntityItem)obj;
					if( (posX - 1 <= entity.posX &&  entity.posX <= posX + 1) &&
						(posY - 5 <= entity.posY &&  entity.posY <= posY + 1) &&
						(posZ - 1 <= entity.posZ &&  entity.posZ <= posZ + 1)
				){
					EntityItem item = (EntityItem)entity;
					itemStack.add(item.getEntityItem());
					item.setDead();
				}
			}
		}
		return itemStack;
	}
}
