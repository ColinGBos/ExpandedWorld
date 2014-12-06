package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FarmerHutGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int base = Math.max(world.getTopSolidOrLiquidBlock(x, z), world.provider.getAverageGroundLevel());
		world.setBlock(x, base + 40, z, Blocks.stone, 0, 2);

		generateBasement(world, rand, x, base - 1, z);
		generateHouse(world, rand, x, base, z);

		
		return true;
	}

	public boolean generateBasement(World world, Random rand, int x, int base, int z)
	{
		int i;
		int j;
		int k;
		
		for (i = 0; i <= 8; i++)
		{
			for (j = 0; j >= -5; j--)
			{
				for (k = 0; k <= 8; k++)
				{
					if (i == 0 || i == 8 || j == -5 || k == 0 || k == 8)
					{
						world.setBlock(x + i, base + j, z + k, Blocks.stonebrick, 0, 2);
					}
					else
					{
						world.setBlockToAir(x + i, base + j, z + k);
					}
				}
			}
		}
		return true;
	}
	
	public boolean generateHouse(World world, Random rand, int x, int base, int z)
	{
		int i;
		int j;
		int k;
		for (i = 0; i <= 8; i++)
		{
			for (j = 0; j <= 4; j++)
			{
				for (k = 0; k <= 8; k++)
				{
					if (i == 0 || i == 8 || j == 0 || j == 4 || k == 0 || k == 8)
					{
						if (j == 4 || j == 0)
						{
							world.setBlock(x + i, base + j, z + k, Blocks.planks, 5, 2);
						}
						else
						{
							world.setBlock(x + i, base + j, z + k, Blocks.planks, 0, 2);
						}
					}
					else
					{
						world.setBlockToAir(x + i, base + j, z + k);
					}
				}
			}
		}
		return true;
	}

}
