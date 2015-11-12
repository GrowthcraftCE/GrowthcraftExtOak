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
