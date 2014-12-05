package com.vapourdrive.expandedworld.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class EW_WorldGenHandler implements IWorldGenerator
{
	private WorldGenerator DarkStoneGen;
	private WorldGenerator FarmerHut;

	// private WorldGenerator ChestGen;

	public EW_WorldGenHandler()
	{
		GameRegistry.registerWorldGenerator(this, 100);
		DarkStoneGen = new DarkStoneGenerator();
		FarmerHut = new FarmerHutGenerator();
		// ChestGen = new ChestGenerator();
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int xChunk = chunkX * 16 + random.nextInt(16);
		int zChunk = chunkZ * 16 + random.nextInt(16);
		int chx = xChunk + random.nextInt(16);
		int chz = zChunk + random.nextInt(16);
		if (random.nextInt(100) == 0)
		{
			DarkStoneGen.generate(world, random, chx, 9, chz);
		}
		else if (random.nextInt(100) == 0)
		{
			FarmerHut.generate(world, random, chx, world.getChunkHeightMapMinimum(chx, chz), chz);
		}
	}

}
