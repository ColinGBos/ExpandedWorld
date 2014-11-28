package com.vapourdrive.expandedworld.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDarkStone extends EWBlock
{

	public static IIcon[] blocks;
	protected BlockDarkStone()
	{
		super(Material.rock);
		setBlockName(BlockInfo.BlockDarkStoneName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blocks = new IIcon[BlockInfo.DarkStoneTypes];

		for (int i = 0; i < (BlockInfo.DarkStoneTypes); ++i)
		{
			blocks[i] = register.registerIcon(BlockInfo.TextureLocation + BlockInfo.BlockDarkStoneName + i);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blocks[meta];
	}

	@Override
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < BlockInfo.DarkStoneTypes; ++i)
		{
			list.add(new ItemStack(block, 1, i));
		}
	}

}
