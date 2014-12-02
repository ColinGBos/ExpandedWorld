package com.vapourdrive.expandedworld.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.vapourdrive.expandedworld.ExpandedWorld;

public class ItemCaveGenerator extends Item
{
	public ItemCaveGenerator()
	{
		this.setCreativeTab(ExpandedWorld.tabExpandedWorld);
		this.setUnlocalizedName(ItemInfo.CaveGeneratorName);
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
	{
		return false;
	}

}
