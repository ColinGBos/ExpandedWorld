package com.vapourdrive.expandedworld.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class EWCreativeTab extends CreativeTabs
{

	public EWCreativeTab(int ID, String name)
	{
		super(ID, name);
		setBackgroundImageName("expandedworld.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTabLabel()
	{
		return "ExpandedWorld";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel()
	{
		return "Expanded World";
	}

	@Override
	public Item getTabIconItem()
	{
		return Items.ender_pearl;
	}

	@Override
	public boolean hasSearchBar()
	{
		return true;
	}

	@Override
	public int getSearchbarWidth()
	{
		return 70;
	}

}
