package com.vapourdrive.expandedworld.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class EW_Blocks
{
	public static Block BlockPlacer;

	public static void init()
	{
		BlockPlacer = new BlockPlacer();

		GameRegistry.registerBlock(BlockPlacer, ItemBlockPlacer.class, BlockInfo.BlockPlacerName);

	}

	public static void registerRecipes()
	{
		// TODO Auto-generated method stub

	}

}
