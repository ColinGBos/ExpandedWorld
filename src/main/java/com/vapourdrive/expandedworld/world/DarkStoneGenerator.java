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
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				for (int k = 0; k < 5; k++)
				{
					if (rand.nextInt(20) == 0 && world.isAirBlock(x + i, y + j, z + k))
					{
						world.setBlock(x + i, y + j, z + k, Blocks.diamond_ore, 0, 3);
					}
					else if(world.isAirBlock(x + i, y + j, z + k))
					{
						world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
					}
				}
			}
		}

		return true;
	}

}
