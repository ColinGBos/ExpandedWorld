package com.vapourdrive.expandedworld.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemDarkStone extends EWItemBlock
{

	public ItemDarkStone(Block block)
	{
		super(block);
		this.hasSubtypes = true;
	}

	@Override
	public int getMetadata(int Meta)
	{
		return Meta;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		int meta = itemstack.getItemDamage();
		if (meta < 0 || meta >= BlockInfo.DarkStoneTypes)
		{
			meta = 0;
		}
		return super.getUnlocalizedName() + meta;
	}

}
