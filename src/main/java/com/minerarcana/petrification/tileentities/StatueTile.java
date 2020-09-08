package com.minerarcana.petrification.tileentities;

import com.minerarcana.petrification.entities.PetrifiedPlayerEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.UUID;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;
import static com.minerarcana.petrification.content.PetrificationEntities.PETRIFIED_PLAYER;

public class StatueTile extends TileEntity {

    private boolean isPlayer;
    private LivingEntity setEntity;
    private CompoundNBT entityNBT;
    private UUID id;
    public StatueTile() {
        super(ENTITY_STATUE.getTileEntityType());
    }

    public void setEntity(LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            isPlayer = true;
            id = ((PlayerEntity) entity).getGameProfile().getId();
        } else {
            this.setEntity = entity;
            id = entity.getUniqueID();
        }
        entityNBT = entity.serializeNBT();
    }

    public void revivifyEntity() {

    }

    public LivingEntity getInternalEntity() {
        if(world != null) {
            if (setEntity == null) {
                updateStatueToClient();
                if (isPlayer()) {
                    if (world != null)
                        setEntity = PETRIFIED_PLAYER.get().create(world);
                    if (setEntity != null) {
                        ((PetrifiedPlayerEntity) setEntity).setPlayerID(id);
                        setEntity.deserializeNBT(entityNBT);
                    }
                } else {
                    setEntity = (LivingEntity) EntityType.readEntityType(entityNBT).get().create(world);
                    if (setEntity != null)
                        setEntity.deserializeNBT(entityNBT);
                }
            }
        }
        return setEntity;
    }

    public void setIsPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void updateStatueToClient(){
        requestModelDataUpdate();
        this.markDirty();
        if(getWorld() != null){
            this.getWorld().notifyBlockUpdate(pos,this.getBlockState(),this.getBlockState(),3);
        }
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(null,pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.deserializeNBT(tag);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        setIsPlayer(nbt.getBoolean("isPlayer"));
        entityNBT = nbt.getCompound("entityNBT");
        id = nbt.getUniqueId("uID");
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("isPlayer", isPlayer());
        compound.putUniqueId("uID", id);
        compound.put("entityNBT", entityNBT);
        return super.write(compound);
    }
}
