package com.minerarcana.petrification.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

import static java.lang.Boolean.FALSE;

public class StoneNestBlock extends Block {

    public static final BooleanProperty EGG = BooleanProperty.create("egg");

    public StoneNestBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1.5F,10F).sound(SoundType.STONE));
        this.setDefaultState(this.stateContainer.getBaseState().with(EGG, FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(EGG, FALSE);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(EGG);
    }



}
