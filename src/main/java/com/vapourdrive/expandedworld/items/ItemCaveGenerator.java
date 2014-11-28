package com.vapourdrive.expandedworld.items;

import com.vapourdrive.expandedworld.ExpandedWorld;
import com.vapourdrive.expandedworld.world.DarkStoneGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCaveGenerator extends Item
{
	public ItemCaveGenerator()
	{
		this.setCreativeTab(ExpandedWorld.tabExpandedWorld);
		this.setUnlocalizedName(ItemInfo.CaveGeneratorName);
	}
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float fx, float fy, float fz)
    {
    	DarkStoneGenerator.generateRoom(world, world.rand, x, y+40, z, 3, 3);
        return false;
    }

}
