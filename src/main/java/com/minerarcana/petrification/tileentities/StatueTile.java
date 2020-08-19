package com.minerarcana.petrification.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;

public class StatueTile extends TileEntity {

    private LivingEntity entity;
    private CompoundNBT nbt;
    private CompoundNBT inventory;

    public StatueTile() {
        super(ENTITY_STATUE.getTileEntityType());
    }

    public LivingEntity getEntity() {
        if(entity == null && nbt != null){
            if(world != null) {
                EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(nbt.getString("typeID")));
                if(type != null){
                    setEntity((LivingEntity) type.create(world));
                    entity.deserializeNBT(nbt);
                }
            }
        }
        return entity;
    }

    public CompoundNBT getInventory() {
        return inventory;
    }

    public void setEntity(LivingEntity entity) {
        updateStatueInternal();
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
        nbt.put("entity",getEntity().serializeNBT());
        nbt.putString("typeID",getEntity().getType().getRegistryName().toString());
        nbt.put("inventory",getInventory());
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        nbt = tag.getCompound("entity");
        nbt.putString("typeID",nbt.getString("typeID"));
        inventory = tag.getCompound("inventory");
        updateStatueInternal();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.nbt = nbt.getCompound("entity");
        this.inventory = nbt.getCompound("inventory");
        nbt.putString("typeID",nbt.getString("typeID"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("entity", getEntity().serializeNBT());
        compound.put("inventory",getInventory());
        compound.putString("typeID",getEntity().getType().getRegistryName().toString());
        return super.write(compound);
    }
}
