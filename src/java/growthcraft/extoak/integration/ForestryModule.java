/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 IceDragon200
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
package growthcraft.extoak.integration;

import java.util.ArrayList;

import growthcraft.bees.common.block.BlockBeeBox;
import growthcraft.bees.common.block.EnumBeeBoxForestry;
import growthcraft.bees.common.item.ItemBlockBeeBox;
import growthcraft.core.common.definition.BlockTypeDefinition;
import growthcraft.core.integration.ModIntegrationBase;
import growthcraft.extoak.common.block.BlockBeeBoxForestry;
import growthcraft.extoak.GrowthCraftOak;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForestryModule extends ModIntegrationBase
{
	public ForestryModule()
	{
		super(GrowthCraftOak.MOD_ID, "Forestry");
	}

	@Override
	protected void doPreInit()
	{
		final int beeboxCount = EnumBeeBoxForestry.VALUES.length;
		GrowthCraftOak.beeBoxesForestry = new ArrayList<BlockTypeDefinition<BlockBeeBox>>(beeboxCount * 2);

		for (EnumBeeBoxForestry en : EnumBeeBoxForestry.VALUES)
		{
			GrowthCraftOak.beeBoxesForestry.add(new BlockTypeDefinition<BlockBeeBox>(new BlockBeeBoxForestry(en, false)));
		}
		for (EnumBeeBoxForestry en : EnumBeeBoxForestry.VALUES)
		{
			GrowthCraftOak.beeBoxesForestry.add(new BlockTypeDefinition<BlockBeeBox>(new BlockBeeBoxForestry(en, true)));
		}
	}

	@Override
	protected void doRegister()
	{
		for (EnumBeeBoxForestry en : EnumBeeBoxForestry.VALUES)
		{
			if (en == null) continue;

			{
				final BlockTypeDefinition<BlockBeeBox> beeBox = en.getBlockDefinition();
				if (beeBox != null)
				{
					beeBox.getBlock().setFlammability(20).setFireSpreadSpeed(5).setHarvestLevel("axe", 0);
					GameRegistry.registerBlock(beeBox.getBlock(), ItemBlockBeeBox.class, "grc.BeeBox.Forestry." + en.name);
				}
			}
			{
				final BlockTypeDefinition<BlockBeeBox> beeBoxFP = en.getBlockDefinitionFireproof();
				if (beeBoxFP != null)
				{
					beeBoxFP.getBlock().setHarvestLevel("axe", 0);
					GameRegistry.registerBlock(beeBoxFP.getBlock(), ItemBlockBeeBox.class, "grc.BeeBox.Forestry." + en.name + "Fireproof");
				}
			}
		}
	}

	@Override
	protected void doLateRegister()
	{
		for (EnumBeeBoxForestry en : EnumBeeBoxForestry.VALUES)
		{
			if (en == null) continue;

			{
				final BlockTypeDefinition<BlockBeeBox> beeBox = en.getBlockDefinition();
				if (beeBox != null)
				{
					final ItemStack planks = en.getForestryPlanksStack();
					if (planks != null)
					{
						GameRegistry.addShapedRecipe(beeBox.asStack(), " A ", "A A", "AAA", 'A', planks);
					}
				}
			}
			{
				final BlockTypeDefinition<BlockBeeBox> beeBoxFP = en.getBlockDefinitionFireproof();
				if (beeBoxFP != null)
				{
					final ItemStack planks = en.getForestryFireproofPlanksStack();
					if (planks != null)
					{
						GameRegistry.addShapedRecipe(beeBoxFP.asStack(), " A ", "A A", "AAA", 'A', planks);
					}
				}
			}
		}
	}
}
