package com.vapourdrive.expandedworld.world;

import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

public class TestStructure extends MapGenStructure
{
	public TestStructure()
	{
		
	}

	@Override
	public String func_143025_a()
	{
		return "TestStructure";
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int x, int z)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected StructureStart getStructureStart(int x, int z)
	{
		return null;
	}

}
