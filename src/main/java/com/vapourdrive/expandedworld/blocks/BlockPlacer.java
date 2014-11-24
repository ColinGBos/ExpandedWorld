package com.vapourdrive.expandedworld.blocks;

import com.vapourdrive.expandedworld.blocks.BlockInfo;
import com.vapourdrive.expandedworld.ExpandedWorld;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPlacer extends Block
{

	protected BlockPlacer()
	{
		super(Material.rock);
		setBlockName(BlockInfo.BlockPlacerName);
		setCreativeTab(ExpandedWorld.tabExpandedWorld);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(BlockInfo.TextureLocation + BlockInfo.BlockPlacerName);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float fx, float fy, float fz)
	{
		ItemStack stack = player.getCurrentEquippedItem();
		int direction = getDirection(side, fx, fy, fz);

		if (world.isRemote)
		{
			System.out.println("fx " + fx);
			System.out.println("fy " + fy);
			System.out.println("fz " + fz);
			System.out.println(side);
		}

		if (stack != null)
		{
			Block placeblock = Block.getBlockFromItem(stack.getItem());
			int placemeta = stack.getItemDamage();
			placeBlock(world, x, y, z, placeblock, placemeta, direction);
		}
		return true;
	}

	public int getDirection(int side, float fx, float fy, float fz)
	{
		float lmin = 0.0625F;
		float lmax = 0.25F;
		float rmin = 0.75F;
		float rmax = 0.9375F;

		if (fx > lmax && fx < rmin)
		{
			if (fz > rmin && fz < rmax)
			{
				if ((side == 1) || (side == 0)) return 3;
			}
			if (fz > lmin && fz < lmax)
			{
				if ((side == 1) || (side == 0)) return 2;
			}
			if (fz > lmax && fz < rmin)
			{
				if (side == 1) return 0;
				if (side == 0) return 1;
			}
		}
		return 1;
	}
	
	public void placeBlock(World world, int x, int y, int z, Block placeblock, int placemeta, int direction)
	{
		switch ( direction )
		{
		case 1: 
		{
			
		}
		}
		
	}

}
