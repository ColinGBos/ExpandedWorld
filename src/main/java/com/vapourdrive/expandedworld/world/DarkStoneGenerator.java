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
		generateRoom(world, rand, x, y, z, 3, 3);
		return true;
	}


	public boolean generateRoom(World world, Random rand, int x, int y, int z, int size, int chance)
	{
		int l = (5 + size);
		int m = (((1 + size) * (1 + size)) * 3);
		int o = (((2 + size) * (2 + size)) * 3);
		int p = (2 + size);
		for (int i = -l; i < l; i++)
		{
			for (int j = -(l - 1); j < (l - 1); j++)
			{
				for (int k = -l; k < l; k++)
				{
					int distVal = (i * i + j * j + k * k);
					/*
					 * if (distVal < 48) { if(world.isAirBlock(x + i, y + j, z +
					 * k)) { world.setBlock(x + i, y + j, z + k, Blocks.air, 0,
					 * 3); } }
					 */
					if (distVal > m && distVal < o && (j >= -p && j <= p))
					{
						if (world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
							if (rand.nextInt(20) == 0)
							{
								world.setBlock(x + i, y + j, z + k, Blocks.glowstone, 0, 3);
							}
						}
					}
					else if (distVal < o && (j == -p || j == p))
					{
						if (world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
							if (rand.nextInt(30) == 0)
							{
								world.setBlock(x + i, y + j, z + k, Blocks.glowstone, 0, 3);
							}
						}
					}
				}
			}
		}

		if (rand.nextInt(chance) == 0)
		{
			return true;
		}
		int next = rand.nextInt(3) + 1;
		generateRoom(world, rand, x + (13 + (next * 2)), y - (size - next), z, next, 3);
		generateHallX(world, rand, x + (4 + size), y - (size), z);
		
		if(rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), z + (13 + (next * 2)), next, 1);
			generateHallZ(world, rand, x, y - (size), z + (4 + size));
		}
		
		if(rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), z - (13 + (next * 2)), next, 1);
			generateHallZ(world, rand, x, y - (size), z - (9 + size));
		}
		
		return true;
	}

	public boolean generateHallX(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				world.setBlock(x - 1, y + i, z + k, Blocks.air, 0, 3);
				world.setBlock(x + 6, y + i, z + k, Blocks.air, 0, 3);
			}
			world.setBlock(x - 1, y = -2, z + i, Blocks.stone, 0, 3);
			world.setBlock(x + 6, y = -2, z + i, Blocks.stone, 0, 3);
		}

		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						world.setBlock(x + i, y + j, z + k, Blocks.air, 0, 3);
					}
					else
					{
						world.setBlock(x + i, y + j, z + k, Blocks.stone, 0, 3);
					}
				}
			}
		}
		return true;
	}

	public boolean generateHallZ(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				world.setBlock(x + k, y + i, z - 1, Blocks.air, 0, 3);
				world.setBlock(x + k, y + i, z + 6, Blocks.air, 0, 3);
			}
			world.setBlock(x + k, y = -2, z + 6, Blocks.stone, 0, 3);
			world.setBlock(x + k, y = -2, z + -1, Blocks.stone, 0, 3);
		}
		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						world.setBlock(x + k, y + j, z + i, Blocks.air, 0, 3);
					}
					else
					{
						world.setBlock(x + k, y + j, z + i, Blocks.stone, 0, 3);
					}
				}
			}
		}
		return true;
	}

}
