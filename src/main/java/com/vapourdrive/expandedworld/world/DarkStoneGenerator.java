package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DarkStoneGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if(world.isAirBlock(x, y, z))
		{
			world.setBlock(x, y, z, Blocks.stone, 0, 3);
		}
		System.out.println("gencheck");
		return true;
	}

}
