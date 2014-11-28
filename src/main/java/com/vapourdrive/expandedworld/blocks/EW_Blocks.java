package com.vapourdrive.expandedworld.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class EW_Blocks
{
	public static Block BlockPlacer;
	public static Block BlockDarkStone;
	public static Block BlockDarkStoneLight;

	public static void init()
	{
		BlockPlacer = new BlockPlacer();
		BlockDarkStone = new BlockDarkStone();
		BlockDarkStoneLight = new BlockDarkStoneLight();

		GameRegistry.registerBlock(BlockPlacer, ItemBlockPlacer.class, BlockInfo.BlockPlacerName);
		GameRegistry.registerBlock(BlockDarkStone, ItemDarkStone.class, BlockInfo.BlockDarkStoneName);
		GameRegistry.registerBlock(BlockDarkStoneLight, ItemDarkStoneLight.class, BlockInfo.BlockDarkStoneLightName);

	}

	public static void registerRecipes()
	{
		// TODO Auto-generated method stub

	}

}
