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
		for (int i = -8; i < 8; i++)
		{
			for (int j = -8; j < 8; j++)
			{
				for (int k = -8; k < 8; k++)
				{
					int distVal = (i*i + j*j + k*k);
					/*
					if (distVal < 48)
					{
						if(world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.air, 0, 3);
						}
					}
					*/
					if(distVal > 48 && distVal < 75 && (j >= -5 && j <= 5))
					{
						if(world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
							if(rand.nextInt(30) == 0)
							{
								world.setBlock(x + i, y + j, z + k, Blocks.glowstone, 0, 3);
							}
						}
					}
					else if(distVal < 75 && (j == -6 || j == 6))
					{
						if(world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
							if(rand.nextInt(30) == 0)
							{
								world.setBlock(x + i, y + j, z + k, Blocks.glowstone, 0, 3);
							}
						}
					}
				}
			}
		}

		return true;
	}

}
