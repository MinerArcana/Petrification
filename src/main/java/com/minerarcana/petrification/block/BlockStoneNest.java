package com.minerarcana.petrification.block;

import com.minerarcana.petrification.Petrification;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class BlockStoneNest extends Block {
    private final static PropertyBool EGG = PropertyBool.create("egg");
    public final static String NAME = "stone_nest";

    public BlockStoneNest() {
        super(Material.ROCK);
        this.setUnlocalizedName(NAME);
        this.setRegistryName(new ResourceLocation(Petrification.MODID, NAME));
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(EGG, false));
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(EGG, meta == 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(EGG) ? 0 : 1;
    }

    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, EGG);
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
    }

}
