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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BlockStoneNest extends Block {
    private final static PropertyBool EGG = PropertyBool.create("egg");
    public static final AxisAlignedBB FULL_BLOCK_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    public final static String NAME = "stone_nest";

    public BlockStoneNest() {
        super(Material.ROCK);
        this.setUnlocalizedName(NAME);
        this.setRegistryName(new ResourceLocation(Petrification.MODID, NAME));
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(EGG, false));
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.STONE);
        this.setTickRandomly(true);
        this.fullBlock = false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
        if (state.getValue(EGG) && world.provider.getMoonPhase(world.getWorldTime()) == 0) {
            spawnCockatrice(world, pos);
        }
    }

    private void spawnCockatrice(World world, BlockPos pos) {

    }

    @Override
    public boolean canPlaceBlockAt(World world, @Nonnull BlockPos pos) {
        BlockPos downPos = pos.down();
        return world.getBlockState(downPos).isSideSolid(world, pos, EnumFacing.UP);
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return FULL_BLOCK_AABB;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
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
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
        items.add(new ItemStack(this, 1, 1));
    }

}
