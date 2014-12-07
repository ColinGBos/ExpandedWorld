package com.vapourdrive.expandedworld.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.vapourdrive.expandedworld.ExpandedWorld;
import com.vapourdrive.expandedworld.world.FarmerHutGenerator;

public class ItemCaveGenerator extends Item
{
	public ItemCaveGenerator()
	{
		this.setCreativeTab(ExpandedWorld.tabExpandedWorld);
		this.setUnlocalizedName(ItemInfo.CaveGeneratorName);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		FarmerHutGenerator.startGenerate(world, world.rand, x, y, z);
		return false;
	}

}
