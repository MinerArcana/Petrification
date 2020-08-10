package com.minerarcana.petrification.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;

public class StatueTile extends TileEntity {

    private LivingEntity entity;
    private CompoundNBT nbt;

    public StatueTile() {
        super(ENTITY_STATUE.getTileEntityType());
    }

    public LivingEntity getEntity() {
        if(entity == null && nbt != null){
            setEntity((LivingEntity) EntityType.loadEntityUnchecked(nbt.getCompound("entity"),world).get());
        }
        return entity;
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.nbt = nbt.getCompound("entity");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("entity", entity.serializeNBT());
        return super.write(compound);
    }
}
