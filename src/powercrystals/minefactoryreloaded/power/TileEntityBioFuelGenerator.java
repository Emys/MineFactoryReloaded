package powercrystals.minefactoryreloaded.power;

import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class TileEntityBioFuelGenerator extends TileEntityLiquidGenerator
{
	public TileEntityBioFuelGenerator()
	{
		super(12, 16, 0);
	}
	
	@Override
	protected LiquidStack getLiquidType()
	{
		return LiquidDictionary.getLiquid("bioFuel", 1);
	}
}
