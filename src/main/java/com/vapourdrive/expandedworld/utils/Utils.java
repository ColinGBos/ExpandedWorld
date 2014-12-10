package com.vapourdrive.expandedworld.utils;

import java.util.Random;

import net.minecraft.world.World;

public class Utils
{
	public static int getDirection(int side, float fx, float fy, float fz)
	{
		float min = 0.0625F;
		float med1 = 0.25F;
		float med2 = 0.75F;
		float max = 0.9375F;

		if (side == 0 || side == 1)
		{
			if (fx > med1 && fx < med2 && fz > min && fz < med1)
			{
				return 2;
			}
			else if (fx > med2 && fx < max && fz > med1 && fz < med2)
			{
				return 5;
			}
			else if (fx > min && fx < med1 && fz > med1 && fz < med2)
			{
				return 4;
			}
			else if (fx > med1 && fx < med2 && fz > med2 && fz < max)
			{
				return 3;
			}
			else if (fx > med1 && fx < med2 && fz > med1 && fz < med2)
			{
				if (side == 0)
				{
					return 1;
				}
				if (side == 1)
				{
					return 0;
				}
			}
		}
		else if (side == 2 || side == 3)
		{
			if (fx > med1 && fx < med2 && fy > min && fy < med1)
			{
				return 0;
			}
			else if (fx > med2 && fx < max && fy > med1 && fy < med2)
			{
				return 5;
			}
			else if (fx > min && fx < med1 && fy > med1 && fy < med2)
			{
				return 4;
			}
			else if (fx > med1 && fx < med2 && fy > med2 && fy < max)
			{
				return 1;
			}
			else if (fx > med1 && fx < med2 && fy > med1 && fy < med2)
			{
				if (side == 2)
					return 3;
				if (side == 3)
					return 2;
			}
		}
		else if (side == 4 || side == 5)
		{
			if (fz > med1 && fz < med2 && fy > min && fy < med1)
			{
				return 0;
			}
			else if (fz > med2 && fz < max && fy > med1 && fy < med2)
			{
				return 3;
			}
			else if (fz > min && fz < med1 && fy > med1 && fy < med2)
			{
				return 2;
			}
			else if (fz > med1 && fz < med2 && fy > med2 && fy < max)
			{
				return 1;
			}
			else if (fz > med1 && fz < med2 && fy > med1 && fy < med2)
			{
				if (side == 4)
					return 5;
				if (side == 5)
					return 4;
			}
		}
		return 1;
	}

	public static boolean isLevelGround(World world, Random rand, int x, int maxX, int z, int maxZ, int difference)
	{
		int i;
		int j;
		int minHeight = world.getTopSolidOrLiquidBlock(x, z);
		int maxHeight = minHeight;

		for (i = -2; i < maxX; i++)
		{
			for (j = -2; j < maxZ; j++)
			{
				int height = world.getTopSolidOrLiquidBlock(x + i, z + j);
				if (height > maxHeight)
				{
					maxHeight = height;
				}
				else if (height < minHeight)
				{
					minHeight = height;
				}
			}
		}

		if ((maxHeight - minHeight) < difference)
		{
			return true;
		}

		return false;
	}
}
