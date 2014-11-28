package com.vapourdrive.expandedworld.world;

import java.util.Random;

import com.vapourdrive.expandedworld.blocks.EW_Blocks;

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

	public static boolean generateRoom(World world, Random rand, int x, int y, int z, int size, int chance)
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
							placeGenBlock(world, rand, x + i, y + j, z + k);
						}
					}
					else if (distVal < o && (j == -p || j == p))
					{
						if (world.isAirBlock(x + i, y + j, z + k))
						{
							placeGenBlock(world, rand, x + i, y + j, z + k);
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

		if (rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), z + (13 + (next * 2)), next, 1);
			generateHallZ(world, rand, x, y - (size), z + (4 + size));
		}

		if (rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), z - (13 + (next * 2)), next, 1);
			generateHallZ(world, rand, x, y - (size), z - (9 + size));
		}

		return true;
	}

	public static boolean generateHallX(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				if(!world.isAirBlock(x - 1, y + i, z + k))
				{
					world.setBlock(x - 1, y + i, z + k, Blocks.air, 0, 3);
				}
				if(!world.isAirBlock(x + 6, y + i, z + k))
				{
					world.setBlock(x + 6, y + i, z + k, Blocks.air, 0, 3);
				}
			}
		}

		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						if(!world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlock(x + i, y + j, z + k, Blocks.air, 0, 3);
						}
					}
					else
					{
						placeGenBlock(world, rand, x + i, y + j, z + k);
					}
				}
			}
		}
		return true;
	}

	public static boolean generateHallZ(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				if(!world.isAirBlock(x + k, y + i, z + 6))
				{
					world.setBlock(x + k, y + i, z + 6, Blocks.air, 0, 3);
				}
				if(!world.isAirBlock(x + k, y + i, z - 1))
				{
					world.setBlock(x + k, y + i, z - 1, Blocks.air, 0, 3);
				}
			}
		}
		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						if(!world.isAirBlock(x + k, y + j, z + i))
						{
							world.setBlock(x + k, y + j, z + i, Blocks.air, 0, 3);
						}
					}
					else
					{
						placeGenBlock(world, rand, x + k, y + j, z + i);
					}
				}
			}
		}
		return true;
	}

	public static boolean placeGenBlock(World world, Random rand, int x, int y, int z)
	{
		if (rand.nextInt(16) == 0)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStoneLight, 0, 3);
		}
		else if (rand.nextInt(3) == 0)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 1, 3);
		}
		else if (rand.nextInt(3) == 0)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 2, 3);
		}
		else
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 0, 3);
		}

		return true;
	}

}
