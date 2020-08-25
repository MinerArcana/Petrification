package com.minerarcana.petrification.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.content.PetrificationBlocks.PETRIFIED_TILE;

public class PetrifiedTile extends TileEntity {

    private Block tileBlock;
    private CompoundNBT tileNBT;

    public PetrifiedTile() {
        super(PETRIFIED_TILE.getTileEntityType());
    }

    public void setTileEntity(TileEntity tileEntity) {
        this.tileBlock = tileEntity.getBlockState().getBlock();
        this.tileNBT = tileEntity.serializeNBT();
    }

    public Block getTileBlock() {
        return tileBlock;
    }

    public CompoundNBT getTileNBT(){
        return tileNBT;
    }

    public void setTileOnRevive(){
        if(world != null) {
            world.setBlockState(pos, getTileBlock().getDefaultState());
            TileEntity tile = world.getTileEntity(pos);
            if(tile != null) {
                tile.deserializeNBT(getTileNBT());
            }
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        tileNBT = nbt.getCompound("tileNBT");
        tileBlock = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(nbt.getString("block")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("tileNBT", getTileNBT());
        compound.putString("block",getTileBlock().getRegistryName().toString());
        return super.write(compound);
    }



}
