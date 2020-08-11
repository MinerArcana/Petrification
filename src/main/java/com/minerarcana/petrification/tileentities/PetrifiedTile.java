package com.minerarcana.petrification.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.content.PetrificationBlocks.PETRIFIED_TILE;

public class PetrifiedTile extends TileEntity {

    private TileEntity tileEntity;

    public PetrifiedTile() {
        super(PETRIFIED_TILE.getTileEntityType());
    }

    public void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }

    public TileEntity getTile() {
        return tileEntity;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        setTileEntity(ForgeRegistries.TILE_ENTITIES.getValue(new ResourceLocation(nbt.getString("tileEntity"))).create());
        tileEntity.deserializeNBT(nbt);
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("tileEntity",getTile().getType().getRegistryName().toString());
        compound.put("tileNBT",tileEntity.serializeNBT());
        return super.write(compound);
    }
}
