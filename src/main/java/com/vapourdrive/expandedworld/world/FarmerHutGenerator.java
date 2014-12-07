package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.vapourdrive.expandedworld.utils.Utils;
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
		int base = world.getTopSolidOrLiquidBlock(x, z);
		if (Utils.isLevelGround(world, rand, x, 10, z, 10, 3))
		{
			buildingCreation(world, rand, x, base, z);
			buildingDecoration(world, rand, x, base, z);
			if (Utils.isLevelGround(world, rand, x + 15, 10, z, 10, 4))
			{
				pastureCreation(world, rand, x + 15, base, z);
			}
			System.out.println(x + y + z);
			return true;
		}

		return false;
	}
	
	public static boolean buildingCreation(World world, Random rand, int x, int y, int z)
	{
		int base = world.getTopSolidOrLiquidBlock(x, z);

		generateBasement(world, rand, x, base - 1, z);
		generateHouse(world, rand, x, base, z);
		generateRoof(world, rand, x, base, z);
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

	public static boolean pastureCreation(World world, Random rand, int x, int y, int z)
	{
		int i;
		int j;
		int base = 0;

		for (i = 0; i < 8; i++)
		{
			for (j = 0; j < 8; j++)
			{
				if (j == 0 || j == 7 || i == 0 || i == 7)
				{
					base = world.getTopSolidOrLiquidBlock(x + i, z + j);
					world.setBlock(x + i, base, z + j, Blocks.fence, 0, 2);

					if (blockCheck(world, x + i, base, z + j))
					{
						world.setBlock(x + i, base + 1, z + j, Blocks.fence, 0, 2);
					}
				}
			}
		}
		
		pastureFilling(world, rand, x, base, z);

		return true;
	}

	public static boolean blockCheck(World world, int x, int y, int z)
	{
		if (!world.isAirBlock(x + 1, y, z))
		{
			return solidBlock(world, x + 1, y, z);
		}
		if (!world.isAirBlock(x - 1, y, z))
		{
			return solidBlock(world, x - 1, y, z);
		}
		if (!world.isAirBlock(x, y, z + 1))
		{
			return solidBlock(world, x, y, z + 1);
		}
		if (!world.isAirBlock(x, y, z + 1))
		{
			return solidBlock(world, x, y, z + 1);
		}

		return false;
	}
	
	public static boolean solidBlock(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if (block != Blocks.fence && block != Blocks.fence_gate && !block.isFoliage(world, x, y, z))
		{
			return false;
		}
		return true;
	}
	
	public static boolean pastureFilling(World world, Random rand, int x, int base, int z)
	{
		for(int i = 0; i < 6; i++)
		{
			if (rand.nextInt(6) != 0)
			{
				EntityVillager entityvillager = new EntityVillager(world, 0);
                entityvillager.setLocationAndAngles((double)x + i + 1, (double)base + 1, (double)z + i + 1, 0.0F, 0.0F);
                world.spawnEntityInWorld(entityvillager);
			}
		}
		return true;
	}


}
