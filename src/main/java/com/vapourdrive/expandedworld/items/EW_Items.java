package com.vapourdrive.expandedworld.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class EW_Items
{
	public static Item CaveGenerator;

	public static void init()
	{
		CaveGenerator = new ItemCaveGenerator();

		GameRegistry.registerItem(CaveGenerator, ItemInfo.CaveGeneratorName);
	}

	public static void registerRecipes()
	{
		// TODO Auto-generated method stub

	}

}
