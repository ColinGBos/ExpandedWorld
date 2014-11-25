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
			System.out.println("fx: " + fx);
			System.out.println("fy: " + fy);
			System.out.println("fz: " + fz);
			System.out.println("side: " + side);
			System.out.println("direction: " + direction);
		}

		if (stack != null)
		{
			Block placeBlock = Block.getBlockFromItem(stack.getItem());
			int placeMeta = stack.getItemDamage();
			initPlace(world, x, y, z, player, placeBlock, placeMeta, direction, stack);
		}
		return true;
	}

	public int getDirection(int side, float fx, float fy, float fz)
	{
		float min = 0.0625F;
		float med1 = 0.25F;
		float med2 = 0.75F;
		float max = 0.9375F;

		if (side == 0 || side == 1)
		{
			if (fx > med1 && fx < med2 && fz > min && fz < med1)
			{
				return 2;
			}
			else if (fx > med2 && fx < max && fz > med1 && fz < med2)
			{
				return 5;
			}
			else if (fx > min && fx < med1 && fz > med1 && fz < med2)
			{
				return 4;
			}
			else if (fx > med1 && fx < med2 && fz > med2 && fz < max)
			{
				return 3;
			}
			else if (fx > med1 && fx < med2 && fz > med1 && fz < med2)
			{
				if (side == 0)
				{
					return 1;
				}
				if (side == 1)
				{
					return 0;
				}
			}
		}
		else if (side == 2 || side == 3)
		{
			if (fx > med1 && fx < med2 && fy > min && fy < med1)
			{
				return 0;
			}
			else if (fx > med2 && fx < max && fy > med1 && fy < med2)
			{
				return 5;
			}
			else if (fx > min && fx < med1 && fy > med1 && fy < med2)
			{
				return 4;
			}
			else if (fx > med1 && fx < med2 && fy > med2 && fy < max)
			{
				return 1;
			}
			else if (fx > med1 && fx < med2 && fy > med1 && fy < med2)
			{
				if (side == 2)
					return 3;
				if (side == 3)
					return 2;
			}
		}
		else if (side == 4 || side == 5)
		{
			if (fz > med1 && fz < med2 && fy > min && fy < med1)
			{
				return 0;
			}
			else if (fz > med2 && fz < max && fy > med1 && fy < med2)
			{
				return 3;
			}
			else if (fz > min && fz < med1 && fy > med1 && fy < med2)
			{
				return 2;
			}
			else if (fz > med1 && fz < med2 && fy > med2 && fy < max)
			{
				return 1;
			}
			else if (fz > med1 && fz < med2 && fy > med1 && fy < med2)
			{
				if (side == 4)
					return 5;
				if (side == 5)
					return 4;
			}
		}
		return 1;
	}

	public void initPlace(World world, int x, int y, int z, EntityPlayer player, Block placeBlock, int placeMeta, int direction,
			ItemStack stack)
	{
		int xPlace = x;
		int yPlace = y;
		int zPlace = z;
		switch (direction)
		{
			case 0:
				yPlace = (y - 1);
				break;
			case 1:
				yPlace = (y + 1);
				break;
			case 2:
				zPlace = (z - 1);
				break;
			case 3:
				zPlace = (z + 1);
				break;
			case 4:
				xPlace = (x - 1);
				break;
			case 5:
				xPlace = (x + 1);
				break;
			default:
				break;
		}

		if (world.isAirBlock(xPlace, yPlace, zPlace))
		{
			blockPlace(world, xPlace, yPlace, zPlace, player, placeBlock, placeMeta, stack);
		}
		return;

	}

	public void blockPlace(World world, int xPlace, int yPlace, int zPlace, EntityPlayer player, Block placeBlock, int placeMeta,
			ItemStack stack)
	{
		if (!world.setBlock(xPlace, yPlace, zPlace, placeBlock, placeMeta, 3))
		{
			return;
		}
		if (world.getBlock(xPlace, yPlace, zPlace) == placeBlock)
		{
			placeBlock.onBlockPlacedBy(world, xPlace, yPlace, zPlace, player, stack);
			placeBlock.onPostBlockPlaced(world, xPlace, yPlace, zPlace, placeMeta);
		}
		return;
	}

}
