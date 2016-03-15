/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015, 2016 IceDragon200
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package growthcraft.extoak;

import java.util.List;

import growthcraft.api.core.module.ModuleContainer;
import growthcraft.api.core.log.ILogger;
import growthcraft.api.core.log.GrcLogger;
import growthcraft.bees.common.block.BlockBeeBox;
import growthcraft.core.common.definition.BlockTypeDefinition;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod;

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

	public static BlockTypeDefinition<BlockBeeBox> beeBoxBiomesOPlenty;
	public static List<BlockTypeDefinition<BlockBeeBox>> beeBoxesForestry;
	public static List<BlockTypeDefinition<BlockBeeBox>> beeBoxesForestryFireproof;

	private ILogger logger = new GrcLogger(MOD_ID);
	private GrcExtOakConfig config = new GrcExtOakConfig();
	private ModuleContainer modules = new ModuleContainer();

	public static GrcExtOakConfig getConfig()
	{
		return instance.config;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		config.setLogger(logger);
		config.load(event.getModConfigurationDirectory(), "growthcraft/extoak.conf");

		if (config.enableForestryIntegration) modules.add(new growthcraft.extoak.integration.ForestryModule());
		if (config.enableBoPIntegration) modules.add(new growthcraft.extoak.integration.BoPModule());
		if (config.enableThaumcraftIntegration) modules.add(new growthcraft.extoak.integration.ThaumcraftModule());

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
