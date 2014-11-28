package com.vapourdrive.expandedworld.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockDarkStoneLight extends EWBlock
{

	protected BlockDarkStoneLight()
	{
		super(Material.rock);
		this.setLightLevel(1.0F);
		this.setBlockName(BlockInfo.BlockDarkStoneLightName);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(BlockInfo.TextureLocation + BlockInfo.BlockDarkStoneLightName);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}

}
