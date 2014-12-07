package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.expandedworld.world.chests.FoodChest;

public class FarmerHutGenerator extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		startGenerate(world, rand, x, y, z);

		return true;
	}
	
	public static boolean startGenerate(World world, Random rand, int x, int y, int z)
	{
		world.setBlock(x, world.getTopSolidOrLiquidBlock(x, z) + 40, z, Blocks.stone, 0, 2);

		buildingCreation(world, rand, x, y, z);
		buildingDecoration(world, rand, x, y, z);
		
		if(rand.nextInt(5) != 0)
		{
			pastureCreation(world, rand, x + 15, y, z);
		}
		
		return true;
	}

	public static boolean buildingCreation(World world, Random rand, int x, int y, int z)
	{
		int base = Math.max(world.getTopSolidOrLiquidBlock(x, z), world.provider.getAverageGroundLevel());

		generateBasement(world, rand, x, base - 1, z);
		generateHouse(world, rand, x, base, z);
		generateRoof(world, rand, x, base, z);
		return true;
	}

	public static boolean pastureCreation(World world, Random rand, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return true;
	}

	public static boolean buildingDecoration(World world, Random rand, int x, int base, int z)
	{
		placeInteriors(world, rand, x, base, z);
		return true;
	}

	public static boolean generateBasement(World world, Random rand, int x, int base, int z)
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

	public static boolean generateHouse(World world, Random rand, int x, int base, int z)
	{
		int i;
		int j;
		int k;

		for (i = 0; i <= 8; i++)
		{
			for (j = -1; j < 4; j++)
			{
				for (k = 0; k <= 8; k++)
				{
					if (i == 0 || i == 8 || j == 0 || j == 4 || j == -1 || k == 0 || k == 8)
					{
						if (j == -1 && world.isAirBlock(x + i, base + j, z + k))
						{
							world.setBlock(x + i, base + j, z + k, Blocks.planks, 5, 2);
						}
						else if (j == 3)
						{
							world.setBlock(x + i, base + j, z + k, Blocks.planks, 5, 2);
						}
						else if (j == 0)
						{
							if (i == 0 || i == 8 || k == 0 || k == 8)
							{
								world.setBlock(x + i, base + j, z + k, Blocks.planks, 5, 2);
							}
						}
						else
						{
							world.setBlock(x + i, base + j, z + k, Blocks.planks, 0, 2);
						}
						if (i == 0 && (k == 0 || k == 8) || i == 8 && (k == 0 || k == 8))
						{
							if (j >= 0 && j < 4)
							{
								world.setBlock(x + i, base + j, z + k, Blocks.log, 1, 2);
							}
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

	public static boolean generateRoof(World world, Random rand, int x, int base, int z)
	{
		int i;
		int j;
		int k;

		for (i = -1; i <= 9; i++)
		{
			for (j = 4; j <= 5; j++)
			{
				for (k = -1; k <= 9; k++)
				{
					if (j == 4)
					{
						world.setBlock(x + i, base + j, z + k, Blocks.planks, 2, 2);
					}
					else if (j == 5 && (i > -1 && i < 9 && k > -1 && k < 9))
					{
						world.setBlock(x + i, base + j, z + k, Blocks.planks, 2, 2);
					}
				}
			}
		}
		return true;
	}

	public static boolean placeInteriors(World world, Random rand, int x, int base, int z)
	{
		placeSupplyChest(world, rand, x + 7, base, z + 5, 0);

		placeDoor(world, rand, x, base, z);
		return true;
	}

	public static boolean placeDoor(World world, Random rand, int x, int base, int z)
	{
		world.setBlockToAir(x, base, z + 4);
		world.setBlockToAir(x, base + 1, z + 4);
		ItemDoor.placeDoorBlock(world, x, base, z + 4, 0, Blocks.wooden_door);

		return true;
	}

	public static boolean placeSupplyChest(World world, Random rand, int x, int base, int z, int check)
	{
		world.setBlock(x, base, z, Blocks.chest, 0, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(x, base, z);

		if (tileentitychest != null)
		{
            WeightedRandomChestContent.generateChestContents(rand, FoodChest.farmerchest, tileentitychest, 5);
            WeightedRandomChestContent.generateChestContents(rand, FoodChest.farmerchest, tileentitychest, 5);
		}
		if (rand.nextInt(10) == 0 && check == 0)
		{
			placeSupplyChest(world, rand, x, base, z + 1, 1);
		}
		return true;
	}

}
