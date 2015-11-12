package growthcraft.extoak.common.block;

import growthcraft.bees.common.block.BlockBeeBox;
import growthcraft.core.common.definition.BlockTypeDefinition;
import growthcraft.extoak.GrowthCraftOak;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public enum EnumBeeBoxForestry
{
	LARCH("larch"),
	TEAK("teak"),
	ACACIA("acacia"),
	LIME("lime"),
	CHESTNUT("chestnut"),
	WENGE("wenge"),
	BAOBAB("baobab"),
	SEQUOIA("sequoia", 4.0f),
	KAPOK("kapok"),
	EBONY("ebony"),
	MAHOGANY("mahogany"),
	BALSA("balsa", 1.0f),
	WILLOW("willow"),
	WALNUT("walnut"),
	GREENHEART("greenheart", 7.5f),
	CHERRY("cherry"),
	MAHOE("mahoe"),
	POPLAR("poplar"),
	PALM("palm"),
	PAPAYA("papaya"),
	PINE("pine", 3.0f),
	PLUM("plum"),
	MAPLE("maple"),
	CITRUS("citrus"),
	GIGANTEUM("giganteum"),
	IPE("ipe"),
	PADAUK("padauk"),
	COCOBOLO("cocobolo"),
	ZEBRAWOOD("zebrawood");

	public static final EnumBeeBoxForestry[] VALUES = values();

	public final String name;
	public final float hardness;

	private EnumBeeBoxForestry(String n, float h)
	{
		this.name = n;
		this.hardness = h;
	}

	private EnumBeeBoxForestry(String n)
	{
		this(n, 2.0f);
	}

	public float getHardness()
	{
		return hardness;
	}

	public NBTTagCompound newWoodTag()
	{
		final NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("WoodType", ordinal());
		return tag;
	}

	public ItemStack getForestryWoodStack(String blockname)
	{
		final Block block = GameRegistry.findBlock("Forestry", blockname);
		if (block != null)
		{
			final ItemStack result = new ItemStack(block);
			result.setTagCompound(newWoodTag());
			return result;
		}
		return null;
	}

	public ItemStack getForestryPlanksStack()
	{
		return getForestryWoodStack("planks");
	}

	public ItemStack getForestryFireproofPlanksStack()
	{
		return getForestryWoodStack("planksFireproof");
	}

	public BlockTypeDefinition<BlockBeeBox> getBlockDefinition()
	{
		if (GrowthCraftOak.beeBoxesForestry != null)
		{
			return GrowthCraftOak.beeBoxesForestry.get(ordinal());
		}
		return null;
	}

	public BlockTypeDefinition<BlockBeeBox> getBlockDefinitionFireproof()
	{
		if (GrowthCraftOak.beeBoxesForestry != null)
		{
			return GrowthCraftOak.beeBoxesForestry.get(VALUES.length + ordinal());
		}
		return null;
	}
}
