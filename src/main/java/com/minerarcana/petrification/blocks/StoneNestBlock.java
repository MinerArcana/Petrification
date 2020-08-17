package com.minerarcana.petrification.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static com.minerarcana.petrification.util.StaticMethodHandler.spawnCockatrice;
import static java.lang.Boolean.FALSE;

public class StoneNestBlock extends Block {

    protected static final VoxelShape EGG_FULL_SHAPE = Block.makeCuboidShape(5D, 1D, 5D, 11.0D, 6D, 11.0D);
    protected static final VoxelShape EGG_EMPTY_SHAPE = Block.makeCuboidShape(2D, 0D, 2D, 14.0D, 10D, 14.0D);

    public static final BooleanProperty EGG = BooleanProperty.create("egg");

    public StoneNestBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1.5F,10F).notSolid().tickRandomly().sound(SoundType.STONE));
        this.setDefaultState(this.stateContainer.getBaseState().with(EGG, FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(EGG, FALSE);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if(state.get(EGG)){
            return VoxelShapes.or(EGG_EMPTY_SHAPE,EGG_FULL_SHAPE);
        }
        return VoxelShapes.or(EGG_EMPTY_SHAPE,Block.makeCuboidShape(5D, 1D, 5D, 11.0D, 6D, 11.0D));
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return VoxelShapes.or(EGG_EMPTY_SHAPE,Block.makeCuboidShape(5D, 1D, 5D, 11.0D, 6D, 11.0D));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(EGG);
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if(state.get(EGG) && !world.isDaytime() && world.func_230315_m_().func_236035_c_(world.getGameTime()) == 5){
            spawnCockatrice(world, pos);
            world.setBlockState(pos, this.getDefaultState().with(EGG, false), 4);
        }
    }


}
