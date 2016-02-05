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
package growthcraft.extoak.common.block;

import java.util.List;

import growthcraft.bees.common.block.BlockBeeBox;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockBeeBoxForestry extends BlockBeeBox
{
	private final EnumBeeBoxForestry beeboxType;
	private final boolean isFireproofFlag;

	public BlockBeeBoxForestry(EnumBeeBoxForestry type, boolean fireproof)
	{
		super();
		setBlockTextureName("grcextoak:beebox");
		this.isFireproofFlag = fireproof;
		this.beeboxType = type;
		setHardness(beeboxType.getHardness());
		setBlockName("grc.BeeBox.Forestry." + beeboxType.name + (isFireproofFlag ? "Fireproof" : ""));
	}

	public boolean isFireproof()
	{
		return isFireproofFlag;
	}

	public EnumBeeBoxForestry getBeeBoxType()
	{
		return beeboxType;
	}

	@Override
	@SuppressWarnings({"rawtypes", "unchecked"})
	public void getSubBlocks(Item block, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(block));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		this.icons = new IIcon[4];
		registerBeeBoxIcons(reg, "/forestry/" + beeboxType.name + "/", 0);
	}
}
