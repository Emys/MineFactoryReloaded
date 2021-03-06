package powercrystals.minefactoryreloaded.modhelpers.ic2;


import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import net.minecraft.item.ItemStack;

import powercrystals.minefactoryreloaded.MineFactoryReloadedCore;
import powercrystals.minefactoryreloaded.MFRRegistry;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.farmables.fertilizables.FertilizerStandard;
import powercrystals.minefactoryreloaded.farmables.harvestables.HarvestableTreeLeaves;
import powercrystals.minefactoryreloaded.farmables.plantables.PlantableStandard;

@Mod(modid = "MineFactoryReloaded|CompatIC2", name = "MFR Compat: IC2", version = MineFactoryReloadedCore.version, dependencies = "after:MineFactoryReloaded;after:IC2")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class IC2
{
	@Init
	public static void load(FMLInitializationEvent e)
	{
		if(!Loader.isModLoaded("IC2"))
		{
			FMLLog.warning("IC2 missing - MFR IC2 Compat not loading");
			return;
		}
		try
		{
			Class<?> ic2Items = Class.forName("ic2.core.Ic2Items");
			if(ic2Items != null)
			{
				ItemStack rubberSapling = (ItemStack)ic2Items.getField("rubberSapling").get(null);
				ItemStack rubberLeaves = (ItemStack)ic2Items.getField("rubberLeaves").get(null);
				ItemStack rubberWood = (ItemStack)ic2Items.getField("rubberWood").get(null);
				ItemStack stickyResin = (ItemStack)ic2Items.getField("resin").get(null);
				
				ItemStack crop = (ItemStack)ic2Items.getField("crop").get(null);
				
				if(rubberSapling != null)
				{
					MFRRegistry.registerPlantable(new PlantableStandard(rubberSapling.itemID, rubberSapling.itemID));
					MFRRegistry.registerFertilizable(new FertilizableIC2RubberTree(rubberSapling.itemID));
				}
				if(rubberLeaves != null)
				{
					MFRRegistry.registerHarvestable(new HarvestableTreeLeaves(rubberLeaves.itemID));
				}
				if(rubberWood != null)
				{
					MFRRegistry.registerHarvestable(new HarvestableIC2RubberWood(rubberWood.itemID, HarvestType.Tree, stickyResin.itemID));
				}
				
				ItemStack fertilizer = (ItemStack)ic2Items.getField("fertilizer").get(null);
				if(fertilizer != null)
				{
					MFRRegistry.registerFertilizer(new FertilizerStandard(fertilizer.itemID, fertilizer.getItemDamage()));
				}
				
				MFRRegistry.registerHarvestable(new HarvestableIC2Crop(crop.itemID));
			}
		}
		catch (Exception x)
		{
			x.printStackTrace();
		}
	}
}
