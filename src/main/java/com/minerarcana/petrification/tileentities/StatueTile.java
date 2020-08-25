package com.minerarcana.petrification.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import java.util.List;
import java.util.UUID;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;

public class StatueTile extends TileEntity {

    private boolean isPlayer;
    private LivingEntity entity;
    private UUID player;
    private CompoundNBT nbt;
    private List<ItemStack> mainInventory;
    private List<ItemStack> armorInventory;

    public StatueTile() {
        super(ENTITY_STATUE.getTileEntityType());
    }

    public LivingEntity getEntity() {
        if(isPlayer){
            PlayerEntity player = world.getPlayerByUuid(this.player);
            if(player != null) {
                PlayerEntity newPlayer = new PlayerEntity(world, pos, player.rotationYaw, player.getGameProfile()) {
                    @Override
                    public boolean isSpectator() {
                        return false;
                    }

                    @Override
                    public boolean isCreative() {
                        return false;
                    }
                };
                newPlayer.deserializeNBT(nbt);
                return newPlayer;
            }
        }
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

    public void setEntity(LivingEntity entity) {
        if(entity instanceof PlayerEntity){
            isPlayer = true;
            PlayerEntity player = ((PlayerEntity) entity);
            this.player = player.getUniqueID();
            this.mainInventory.addAll(player.inventory.mainInventory);
            this.armorInventory.addAll(player.inventory.armorInventory);
        }else {
            this.entity = entity;
        }
        updateStatueInternal();
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void revivifyEntity(){

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
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        nbt = tag.getCompound("entity");
        nbt.putString("typeID",nbt.getString("typeID"));
        updateStatueInternal();
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.nbt = nbt.getCompound("entity");
        nbt.putString("typeID",nbt.getString("typeID"));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("entity", getEntity().serializeNBT());
        compound.putString("typeID",getEntity().getType().getRegistryName().toString());
        return super.write(compound);
    }
}
