package com.vapourdrive.expandedworld.blocks;

import com.vapourdrive.expandedworld.blocks.BlockInfo;
import com.vapourdrive.expandedworld.utils.Utils;
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
		int direction = Utils.getDirection(side, fx, fy, fz);

		if (stack != null)
		{
			int placeMeta = stack.getItemDamage();
			if (initPlace(world, x, y, z, player, placeMeta, direction, side, stack, fx, fy, fz))
			{
				if(player.capabilities.isCreativeMode)
				{
					stack.stackSize++;
				}
				return true;
			}
		}
		return false;
	}

	public boolean initPlace(World world, int x, int y, int z, EntityPlayer player, int placeMeta, int direction, int side, 
			ItemStack stack, float fx, float fy, float fz)
	{
		int xPlace = x;
		int yPlace = y;
		int zPlace = z;
		switch (direction)
		{
			case 0:
			{
				for (int i = 0; i < 16; i++)
				{
					yPlace = (y - (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			case 1:
			{
				for (int i = 0; i < 16; i++)
				{
					yPlace = (y + (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			case 2:
			{
				for (int i = 0; i < 16; i++)
				{
					zPlace = (z - (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			case 3:
			{
				for (int i = 0; i < 16; i++)
				{
					zPlace = (z + (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			case 4:
			{
				for (int i = 0; i < 16; i++)
				{
					xPlace = (x - (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			case 5:
			{
				for (int i = 0; i < 16; i++)
				{
					xPlace = (x + (1 + (1 * i)));
					if (world.isAirBlock(xPlace, yPlace, zPlace))
					{
						if (stack.getItem().onItemUse(stack, player, world, xPlace, yPlace, zPlace, side, fx, fy, fz))
						{
							return true;
						}
					}
				}
				break;
			}
			default:
				break;
		}
		return false;
	}

}
