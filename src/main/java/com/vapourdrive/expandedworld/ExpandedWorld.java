package com.vapourdrive.expandedworld;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vapourdrive.expandedworld.blocks.EW_Blocks;
import com.vapourdrive.expandedworld.config.ConfigHandler;
import com.vapourdrive.expandedworld.creativetab.EWCreativeTab;
import com.vapourdrive.expandedworld.events.FMLEventHooks;
import com.vapourdrive.expandedworld.events.ForgeEventHooks;
import com.vapourdrive.expandedworld.items.EW_Items;
import com.vapourdrive.expandedworld.materials.EW_Materials;
import com.vapourdrive.expandedworld.oredictionary.OreDictionaryRegistry;
import com.vapourdrive.expandedworld.proxies.CommonProxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EWModInfo.ModID, version = EWModInfo.ModVersion)
public class ExpandedWorld
{
	@Instance(EWModInfo.ModID)
	public static ExpandedWorld instance;

	@SidedProxy(clientSide = "com.vapourdrive.expandedworld.proxies.ClientProxy", serverSide = "com.vapourdrive.expandedworld.proxies.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs tabExpandedWorld;
	public static String configPath;

	public static final Logger log = LogManager.getLogger(EWModInfo.ModName);

	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{

		configPath = event.getModConfigurationDirectory() + "/expandedworld/";
		tabExpandedWorld = new EWCreativeTab(CreativeTabs.getNextID(), "tabExpandedWorld");
		

		MinecraftForge.EVENT_BUS.register(new ForgeEventHooks());
		FMLCommonHandler.instance().bus().register(new FMLEventHooks());

		ConfigHandler.init(configPath);
		proxy.initSounds();
		proxy.initRenderers();

		EW_Materials.init();
		EW_Items.init();
		EW_Blocks.init();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{

		EW_Items.registerRecipes();

		EW_Blocks.registerRecipes();
		OreDictionaryRegistry.init();

		/*
		 * ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem( new
		 * WeightedRandomChestContent(new ItemStack(AD_Items.chestKey), 1, 1,
		 * 30));
		 */

		/*
		 * if (WorldInfo.GENERATE_WORLD == false) { new WorldStoneGeneration();
		 * }
		 */
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
