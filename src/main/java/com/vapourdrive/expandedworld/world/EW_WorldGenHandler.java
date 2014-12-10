package com.vapourdrive.expandedworld.world;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class EW_WorldGenHandler implements IWorldGenerator
{
	private WorldGenerator DarkStoneGen;
	private WorldGenerator FarmerHut;
	private TestStructure teststructure;

	// private WorldGenerator ChestGen;

	public EW_WorldGenHandler()
	{
		GameRegistry.registerWorldGenerator(this, 0);
		DarkStoneGen = new DarkStoneGenerator();
		FarmerHut = new FarmerHutGenerator();
		teststructure = (TestStructure) TerrainGen.getModdedMapGen(teststructure, SCATTERED_FEATURE);
		// ChestGen = new ChestGenerator();
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		int xChunk = chunkX * 16 + random.nextInt(16);
		int zChunk = chunkZ * 16 + random.nextInt(16);
		int chx = xChunk + random.nextInt(16);
		int chz = zChunk + random.nextInt(16);

		BiomeGenBase Biome = world.getBiomeGenForCoords(chx, chz);

		if (random.nextInt(100) == 0)
		{
			DarkStoneGen.generate(world, random, chx, 9, chz);
		}
		if (random.nextInt(100) == 0)
		{
			// if (BiomeDictionary.isBiomeOfType(Biome, Type.PLAINS))
			// {
			FarmerHut.generate(world, random, chx, world.getChunkHeightMapMinimum(chx, chz), chz);
			// }
			if (!teststructure.hasStructureAt(chx, 20, chz))
			{
				teststructure.generateStructuresInChunk(world, random, chx, chz);
			}
		}
	}

}
