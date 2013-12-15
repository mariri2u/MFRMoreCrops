package mariri.mfrmorecrops;


import net.minecraftforge.common.Configuration;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableCropPlant;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableStandard;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="MFRMoreCropsMod", name="MFR MoreCrops", version="1.0.0", dependencies = "required-after:MineFactoryReloaded")
@NetworkMod(clientSideRequired=false)
public class MFRMoreCrops {

        // The instance of your mod that Forge uses.
        @Instance(value = "MFRMoreCropsMod")
        public static MFRMoreCrops instance;
        
        // ids
        private int[] woodIds;
        private int[] leaveIds;
        private int[] saplingIds;
        private int[][] seedIds;
        private int[][] cropIds;
//        private int[][] shearsCropIds;
        
        @EventHandler // used in 1.6.2
        //@PreInit    // used in 1.5.2
        public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
            Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	        config.load();
	        int[] defValue = new int[] { -1 };
            //Notice there is nothing that gets the value of this property so the expression results in a Property object.
	        woodIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "WoodIds", "-1").getString(), ",");
	        leaveIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "LeaveIds", "-1").getString(), ",");
	        saplingIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "SaplingIds", "-1").getString(), ",");
	        seedIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "SeedIds", "-1->-1").getString(), ",", "->");
	        cropIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "CropIds", "-1:-1").getString(), ",", ":");
//	        shearsCropIds = stringToInt(config.get(Configuration.CATEGORY_GENERAL, "ShearsCropIds", "-1:-1").getString(), ",", ":");
	        config.save();
        }
        
        private static int[] stringToInt(String str, String separator){
	        String[] aaa = str.split(separator);
	        int[] ids = new int[aaa.length];
            for(int i = 0; i < aaa.length; i++){
            	ids[i]= Integer.parseInt(aaa[i].trim());
            }
            return ids;
        }
        
        private static int[][] stringToInt(String str, String separator1, String separator2){
        	String[] aaa = str.split(separator1);
        	int[][] ids = new int[aaa.length][2];
        	for(int i = 0; i < aaa.length; i++){
        		ids[i] = stringToInt(aaa[i], separator2);
        	}
        	return ids;
        }
        
       
        @EventHandler // used in 1.6.2
        //@PostInit   // used in 1.5.2
        public void postInit(FMLPostInitializationEvent event) {
            // Woods
        	if(saplingIds[0] != -1){
	        	for(int id : saplingIds){
	        		FactoryRegistry.registerPlantable(new PlantableStandard(id, id));
	        	}
        	}
        	
        	if(woodIds[0] != -1){
	        	for(int id : woodIds){
	        		FactoryRegistry.registerHarvestable(new HarvestableAnyDrops(id, HarvestType.Tree));
	        	}
        	}
        	
        	if(leaveIds[0] != -1){
	        	for(int id : leaveIds){
	        		FactoryRegistry.registerHarvestable(new HarvestableTreeLeavesAnyDrops(id));
	        	}
        	}
        	
        	// Crops
        	if(seedIds[0][0] != -1){
        		for(int[] id : seedIds){
                	FactoryRegistry.registerPlantable(new PlantableCropPlant(id[0], id[1]));
        		}
        	}
        	
        	if(cropIds[0][0] != -1){
        		for(int[] id : cropIds){
        			FactoryRegistry.registerHarvestable(new HarvestableCropPlantAnyDrops(id[0], id[1]));
        		}
        	}
        	
//        	if(shearsCropIds[0][0] != -1){
//        		for(int[] id : shearsCropIds){
//        			FactoryRegistry.registerHarvestable(new HarvestableCropPlantShears(id[0], id[1]));
//        		}
//        	}
        }
        
        @EventHandler // used in 1.6.2
        //@Init       // used in 1.5.2
        public void load(FMLInitializationEvent event) {
        }
        
        public static void main(String[] args){
        	stringToInt("-1->-1", ",", "->");
        }
}