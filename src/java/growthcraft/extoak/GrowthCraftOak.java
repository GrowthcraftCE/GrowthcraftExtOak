package growthcraft.extoak;

import java.util.List;

import growthcraft.bees.common.block.BlockBeeBox;
import growthcraft.bees.common.CommonProxy;
import growthcraft.bees.common.item.ItemBlockBeeBox;
import growthcraft.core.common.definition.BlockTypeDefinition;
import growthcraft.core.common.ModuleContainer;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

@Mod(
	modid = GrowthCraftOak.MOD_ID,
	name = GrowthCraftOak.MOD_NAME,
	version = GrowthCraftOak.MOD_VERSION,
	dependencies = "required-after:Growthcraft;required-after:Growthcraft|Bees;after:Forestry"
)
public class GrowthCraftOak
{
	public static final String MOD_ID = "GrowthcraftExt|Oak";
	public static final String MOD_NAME = "GrowthcraftExt Oak";
	public static final String MOD_VERSION = "@VERSION@";

	@Instance(MOD_ID)
	public static GrowthCraftOak instance;

	public static List<BlockTypeDefinition<BlockBeeBox>> beeBoxesForestry;

	private GrcExtOakConfig config = new GrcExtOakConfig();
	private ModuleContainer modules = new ModuleContainer();

	public static GrcBeesConfig getConfig()
	{
		return instance.config;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config.load(event.getModConfigurationDirectory(), "growthcraft/extoak.conf");

		modules.add(new growthcraft.extoak.integration.ForestryModule());

		modules.preInit();
		register();
	}

	private void register()
	{
		modules.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		modules.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		modules.postInit();
	}
}
