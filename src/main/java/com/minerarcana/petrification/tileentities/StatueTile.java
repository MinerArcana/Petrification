package com.minerarcana.petrification.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;

public class StatueTile extends TileEntity {

    private LivingEntity entity;
    private CompoundNBT nbt;

    public StatueTile() {
        super(ENTITY_STATUE.getTileEntityType());
    }

    public LivingEntity getEntity() {
        if(nbt == null){
            updateStatueInternal();
        }
        if(entity == null && nbt != null){
            setEntity((LivingEntity) EntityType.loadEntityUnchecked(nbt.getCompound("entity"),world).get());
        }
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    private void updateStatueInternal(){
        requestModelDataUpdate();
        this.markDirty();
        if (this.getWorld() != null) {
            this.getWorld().notifyBlockUpdate(pos, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(),-1,this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(null,pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("entity",entity.serializeNBT());
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        nbt = tag.getCompound("entity");
        updateStatueInternal();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.nbt = nbt.getCompound("entity");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("entity", getEntity().serializeNBT());
        return super.write(compound);
    }
}
