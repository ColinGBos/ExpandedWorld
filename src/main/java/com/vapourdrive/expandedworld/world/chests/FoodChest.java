package com.vapourdrive.expandedworld.world.chests;

import net.minecraft.init.Items;
import net.minecraft.util.WeightedRandomChestContent;

public class FoodChest
{
	public static int amount = 1;
	public static int chance = 1;

	public static WeightedRandomChestContent[] farmerchest = new WeightedRandomChestContent[]
	{
			new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 2 * chance),
			new WeightedRandomChestContent(Items.bread, 0, 2 * amount, 5 * amount, 6 * chance),
			new WeightedRandomChestContent(Items.wheat, 0, 2 * amount, 5 * amount, 8 * chance),
			new WeightedRandomChestContent(Items.gunpowder, 0, 1, 1 * amount, 1 * chance),
			new WeightedRandomChestContent(Items.string, 0, 1, 2 * amount, 2 * chance),
			new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 2 * chance),
			new WeightedRandomChestContent(Items.apple, 0, 2 * amount, 5 * amount, 2 * chance),
			new WeightedRandomChestContent(Items.baked_potato, 0, 2 * amount, 4 * amount, 6 * chance),
			new WeightedRandomChestContent(Items.bowl, 0, 1, 2 * amount, 5 * chance),
			new WeightedRandomChestContent(Items.arrow, 0, 1, 3 * amount, 2 * chance),
			new WeightedRandomChestContent(Items.carrot, 0, 2 * amount, 5 * amount, 8 * chance),
			new WeightedRandomChestContent(Items.cooked_beef, 0, 2 * amount, 4 * amount, 6 * chance),
			new WeightedRandomChestContent(Items.iron_hoe, 0, 1, 1 * amount, 1 * chance)
	};

}
