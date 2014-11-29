package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.expandedworld.blocks.EW_Blocks;

public class DarkStoneGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		return generateRoom(world, rand, x, y, z, 3, 3);
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
		generateRoom(world, rand, (x + 13 + next + size), y - (size - next), z, next, 3);
		generateHallX(world, rand, x + (4 + size), y - (size), z);

		if (rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), (z + 13 + next + size), next, 1);
			generateHallZ(world, rand, x, y - (size), z + (4 + size));
		}

		if (rand.nextInt(3) == 0)
		{
			generateRoom(world, rand, x, y - (size - next), z - (13 + next + size), next, 1);
			generateHallZ(world, rand, x, y - (size), z - (9 + size));
		}

		return true;
	}

	public void generateHallX(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;

		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						if (!world.isAirBlock(x + i, y + j, z + k))
						{
							world.setBlockToAir(x + i, y + j, z + k);
						}
					}
					else
					{
						placeGenBlock(world, rand, x + i, y + j, z + k);
					}
				}
			}
		}
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				if (!world.isAirBlock(x - 1, y + i, z + k))
				{
					world.setBlockToAir(x - 1, y + i, z + k);
				}
				if (!world.isAirBlock(x - 2, y + i, z + k))
				{
					world.setBlockToAir(x - 2, y + i, z + k);
				}
				if (!world.isAirBlock(x + 6, y + i, z + k))
				{
					world.setBlockToAir(x + 6, y + i, z + k);
				}
				if (!world.isAirBlock(x + 7, y + i, z + k))
				{
					world.setBlockToAir(x + 7, y + i, z + k);
				}
			}
		}
		return;
	}

	public void generateHallZ(World world, Random rand, int x, int y, int z)
	{
		int i = 0;
		int k = 0;

		for (i = 0; i < 6; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (k = -2; k <= 2; k++)
				{
					if (j > -2 && j < 2 && k > -2 && k < 2)
					{
						if (!world.isAirBlock(x + k, y + j, z + i))
						{
							world.setBlockToAir(x + k, y + j, z + i);
						}
					}
					else
					{
						placeGenBlock(world, rand, x + k, y + j, z + i);
					}
				}
			}
		}
		for (i = -1; i < 2; i++)
		{
			for (k = -1; k < 2; k++)
			{
				if (!world.isAirBlock(x + k, y + i, z + 6))
				{
					world.setBlockToAir(x + k, y + i, z + 6);
				}
				if (!world.isAirBlock(x + k, y + i, z + 7))
				{
					world.setBlockToAir(x + k, y + i, z + 7);
				}
				if (!world.isAirBlock(x + k, y + i, z - 1))
				{
					world.setBlockToAir(x + k, y + i, z - 1);
				}
				if (!world.isAirBlock(x + k, y + i, z - 2))
				{
					world.setBlockToAir(x + k, y + i, z - 2);
				}
			}
		}
		return;
	}

	public void placeGenBlock(World world, Random rand, int x, int y, int z)
	{
		int random = rand.nextInt(5);
		
		if (random == 0)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStoneLight, 0, 3);
			return;
		}
		if (random == 1)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 1, 3);
			return;
		}
		if (random == 2)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 2, 3);
			return;
		}
		else
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 0, 3);
			return;
		}

	}

}
