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
package growthcraft.extoak.integration;

import java.util.ArrayList;

import growthcraft.bees.common.block.BlockBeeBox;
import growthcraft.bees.common.item.ItemBlockBeeBox;
import growthcraft.core.common.definition.BlockTypeDefinition;
import growthcraft.core.integration.ModIntegrationBase;
import growthcraft.extoak.common.block.BlockBeeBoxForestry;
import growthcraft.extoak.common.block.EnumBeeBoxForestry;
import growthcraft.extoak.GrowthCraftOak;

import cpw.mods.fml.common.registry.GameRegistry;
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
		GrowthCraftOak.beeBoxesForestry = new ArrayList<BlockTypeDefinition<BlockBeeBox>>();
		GrowthCraftOak.beeBoxesForestryFireproof = new ArrayList<BlockTypeDefinition<BlockBeeBox>>();

		int i = 0;
		int offset = 0;
		for (EnumBeeBoxForestry[] row : EnumBeeBoxForestry.ROWS)
		{
			final BlockTypeDefinition<BlockBeeBox> beeBox = new BlockTypeDefinition(new BlockBeeBoxForestry(row, offset, i, false));
			final BlockTypeDefinition<BlockBeeBox> beeBoxFP = new BlockTypeDefinition(new BlockBeeBoxForestry(row, offset, i, true));
			beeBox.getBlock().setFlammability(20).setFireSpreadSpeed(5).setHarvestLevel("axe", 0);
			beeBoxFP.getBlock().setHarvestLevel("axe", 0);
			GrowthCraftOak.beeBoxesForestry.add(beeBox);
			GrowthCraftOak.beeBoxesForestryFireproof.add(beeBoxFP);
			beeBox.register(String.format("grc.BeeBox.Forestry.%d.%s", i, "Normal"), ItemBlockBeeBox.class);
			beeBoxFP.register(String.format("grc.BeeBox.Forestry.%d.%s", i, "Fireproof"), ItemBlockBeeBox.class);
			i++;
			offset += row.length;
		}
	}

	@Override
	protected void doLateRegister()
	{
		for (EnumBeeBoxForestry en : EnumBeeBoxForestry.VALUES)
		{
			if (en == null) continue;

			{
				final BlockTypeDefinition<BlockBeeBox> beeBox = GrowthCraftOak.beeBoxesForestry.get(en.row);
				if (beeBox != null)
				{
					final ItemStack planks = en.getForestryPlanksStack();
					if (planks != null)
					{
						GameRegistry.addShapedRecipe(beeBox.asStack(1, en.col), " A ", "A A", "AAA", 'A', planks);
					}
				}
			}
			{
				final BlockTypeDefinition<BlockBeeBox> beeBoxFP = GrowthCraftOak.beeBoxesForestryFireproof.get(en.row);
				if (beeBoxFP != null)
				{
					final ItemStack planks = en.getForestryFireproofPlanksStack();
					if (planks != null)
					{
						GameRegistry.addShapedRecipe(beeBoxFP.asStack(1, en.col), " A ", "A A", "AAA", 'A', planks);
					}
				}
			}
		}
	}
}
