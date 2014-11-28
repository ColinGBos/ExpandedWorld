package com.vapourdrive.expandedworld.blocks;

import com.vapourdrive.expandedworld.ExpandedWorld;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class EWBlock extends Block
{

	protected EWBlock(Material material)
	{
		super(material);
		this.setCreativeTab(ExpandedWorld.tabExpandedWorld);
	}

}
