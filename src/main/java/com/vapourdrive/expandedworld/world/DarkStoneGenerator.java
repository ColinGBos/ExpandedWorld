package com.vapourdrive.expandedworld.world;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

import com.vapourdrive.expandedworld.blocks.EW_Blocks;

public class DarkStoneGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		return generateRoom(world, rand, x, y, z, 3, 5);
	}

	public boolean generateRoom(World world, Random rand, int x, int y, int z, int size, int chance)
	{
		if (chance == 0)
		{
			return false;
		}

		roomCreation(world, rand, x, y, z, size);
		roomDecoration(world, rand, x, y, z, size);

		if (rand.nextInt(chance) == 0)
		{
			return true;
		}

		int next = rand.nextInt(3) + 1;
		generateRoom(world, rand, (x + 13 + next + size), y - (size - next), z, next, chance - 1);
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
		
		//world.getChunkFromChunkCoords(x, z).setChunkModified();

		return true;
	}

	public void roomCreation(World world, Random rand, int x, int y, int z, int size)
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
		return;
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
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStoneLight, 0, 2);
			return;
		}
		if (random == 1)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 1, 2);
			return;
		}
		if (random == 2)
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 2, 2);
			return;
		}
		else
		{
			world.setBlock(x, y, z, EW_Blocks.BlockDarkStone, 0, 2);
			return;
		}
	}

	public void roomDecoration(World world, Random rand, int x, int y, int z, int size)
	{
		roofRoughener(world, rand, x, y, z, size);
		roomType(world, rand, x, y, z, size);
		return;
	}

	public void roofRoughener(World world, Random rand, int x, int y, int z, int size)
	{
		for (int i = -(size + 2); i <= (size + 2); i++)
		{
			for (int j = -(size + 2); j <= (size + 2); j++)
			{
				if (rand.nextInt(8) != 0)
				{
					placeGenBlock(world, rand, x + i, y + (size + 1), z + j);
					if (rand.nextInt(7) == 0)
					{
						placeGenBlock(world, rand, x + i, y + (size), z + j);
					}
				}
			}
		}
		return;
	}

	public void roomType(World world, Random rand, int x, int y, int z, int size)
	{
		int type = rand.nextInt(3);

		if (type == 0)
		{
			if (size == 3)
			{
				createFountain(world, rand, x, y, z);
			}
		}

		if (type == 1 && size != 0)
		{
			genChestPlan(world, rand, x, y, z, size);
		}

		if (type == 2)
		{
			if (size == 2 || size == 3)
			{
				createGarden(world, rand, x, y, z, size);
			}
		}

		return;
	}

	public void createFountain(World world, Random rand, int x, int y, int z)
	{
		int i;
		int j;

		for (i = -2; i <= 2; i++)
		{
			for (j = -2; j <= 2; j++)
			{
				if (!(j > -2 && j < 2 && i > -2 && i < 2))
				{
					world.setBlock(x + i, y - 5, z + j, EW_Blocks.BlockDarkStone, 1, 2);
				}
				else
				{
					world.setBlockToAir(x + i, y - 5, z + j);
				}
			}
		}

		for (i = -5; i <= -3; i++)
		{
			world.setBlock(x, y + i, z, EW_Blocks.BlockDarkStone, 0, 2);
		}
		world.setBlock(x, y - 2, z, EW_Blocks.BlockDarkStoneLight, 0, 2);
		world.setBlock(x, y - 1, z, Blocks.flowing_water, 0, 2);

		return;
	}

	public void genChestPlan(World world, Random rand, int x, int y, int z, int size)
	{
		int height = y - 1 - size;
		if (rand.nextInt(3) != 0)
		{
			genChest(world, rand, x + 2 + size, height, z + 2);
		}
		if (rand.nextInt(3) != 0)
		{
			genChest(world, rand, x - 2 - size, height, z - 2);
		}
		if (rand.nextInt(3) != 0)
		{
			genChest(world, rand, x + 2, height, z + 2 + size);
		}
		if (rand.nextInt(3) != 0)
		{
			genChest(world, rand, x - 2, height, z - 2 - size);
		}
		return;

	}

	public void genChest(World world, Random rand, int x, int y, int z)
	{
		world.setBlock(x, y, z, Blocks.chest, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(x, y, z);

		if (tileentitychest != null)
		{
			WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(DUNGEON_CHEST, rand), tileentitychest,
					ChestGenHooks.getCount(DUNGEON_CHEST, rand) / 3);
			WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(ChestGenHooks.MINESHAFT_CORRIDOR, rand),
					tileentitychest, ChestGenHooks.getCount(ChestGenHooks.MINESHAFT_CORRIDOR, rand) / 3);
			WeightedRandomChestContent.generateChestContents(rand, ChestGenHooks.getItems(ChestGenHooks.VILLAGE_BLACKSMITH, rand),
					tileentitychest, ChestGenHooks.getCount(ChestGenHooks.VILLAGE_BLACKSMITH, rand) / 3);

		}
		return;
	}

	public void createGarden(World world, Random rand, int x, int y, int z, int size)
	{
		int height = y - 1 - size;
		for (int i = 1 - size; i <= -1 + size; i++)
		{
			world.setBlock(x + i, height - 1, z + 2, Blocks.farmland, 0, 2);
			world.setBlock(x + i, height - 1, z + 1, Blocks.farmland, 0, 2);
			world.setBlock(x + i, height - 1, z - 1, Blocks.farmland, 0, 2);
			world.setBlock(x + i, height - 1, z - 2, Blocks.farmland, 0, 2);
			world.setBlock(x + i, height, z + 2, Blocks.carrots, 0, 2);
			world.setBlock(x + i, height, z + 1, Blocks.potatoes, 0, 2);
			world.setBlock(x + i, height, z - 1, Blocks.carrots, 0, 2);
			world.setBlock(x + i, height, z - 2, Blocks.potatoes, 0, 2);
			if (i != 0 && size == 3)
			{
				world.setBlock(x + i, height, z + 3, Blocks.fence, 0, 2);
				world.setBlock(x + i, height, z - 3, Blocks.fence, 0, 2);
				world.setBlock(x + 3, height, z + i, Blocks.fence, 0, 2);
				world.setBlock(x - 3, height, z + i, Blocks.fence, 0, 2);
			}
		}

		world.setBlock(x, height - 1, z + 3, Blocks.water, 0, 2);
		world.setBlock(x, height - 1, z - 3, Blocks.water, 0, 2);
		world.setBlock(x + 3, height, z + 3, Blocks.fence, 0, 2);
		world.setBlock(x + 3, height, z - 3, Blocks.fence, 0, 2);
		world.setBlock(x - 3, height, z + 3, Blocks.fence, 0, 2);
		world.setBlock(x - 3, height, z - 3, Blocks.fence, 0, 2);

		return;
	}

}
