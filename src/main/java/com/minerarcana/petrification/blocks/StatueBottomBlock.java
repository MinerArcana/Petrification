package com.minerarcana.petrification.blocks;

import com.minerarcana.petrification.tileentities.StatueTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class StatueBottomBlock extends Block {

    public StatueBottomBlock() {
        super(Properties.create(Material.ROCK).notSolid());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StatueTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }


}
