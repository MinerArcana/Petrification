package com.minerarcana.petrification.blocks;

import com.minerarcana.petrification.tileentities.PetrifiedTile;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.minerarcana.petrification.content.PetrificationBlocks.PETRIFIED_TILE;

public class PetrifiedTileBlock extends Block {

    public PetrifiedTileBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(1.5F,10F).sound(SoundType.STONE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PetrifiedTile();
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if(tile != null){
            if(tile instanceof PetrifiedTile){
                PetrifiedTile pT = (PetrifiedTile)tile;
                ItemStack stack = new ItemStack(PETRIFIED_TILE.getItem());
                CompoundNBT nbt = pT.serializeNBT();
                stack.setTag(nbt);
                worldIn.addEntity(new ItemEntity(worldIn,pos.getX(),pos.getY(),pos.getZ(),stack));
            }
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }

}
