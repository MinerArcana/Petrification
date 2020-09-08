package com.minerarcana.petrification.entities;

import com.minerarcana.petrification.util.AstralSerializers;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.HandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

//Thanks Alan! Almost all of this comes from the PhysicalBodyEntity in Miner Arcana's Astral written by Alan aka Starbreaker20

public class PetrifiedPlayerEntity extends LivingEntity {

    private final ItemStackHandler mainInventory = new ItemStackHandler(36);
    private final ItemStackHandler armorInventory = new ItemStackHandler(4);
    private final ItemStackHandler handsInventory = new ItemStackHandler(2);
    private UUID playerID;
    private NetworkPlayerInfo playerInfo;

    public PetrifiedPlayerEntity(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public void setPlayerID(UUID id){
        this.playerID = id;
    }

    public NetworkPlayerInfo getPlayerInfo() {
        if(playerInfo == null){
            this.playerInfo = Minecraft.getInstance().getConnection().getPlayerInfo(this.getUniqueID());
        }
        return playerInfo;
    }

    public UUID getInternalID() {
        return playerID;
    }

    private ItemStackHandler getArmor() {
        return armorInventory;
    }

    private ItemStackHandler getHands() {
        return handsInventory;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        NonNullList<ItemStack> itemStackList = NonNullList.withSize(4, ItemStack.EMPTY);
        int bound = itemStackList.size();
        IntStream.range(0, bound).forEach(i -> itemStackList.set(i, getArmor().getStackInSlot(i)));
        return itemStackList;
    }

    @Override
    public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
        switch (slotIn.getSlotType()) {
            case HAND:
                return getHands().getStackInSlot(slotIn.getIndex());
            case ARMOR:
                return getArmor().getStackInSlot(slotIn.getIndex());
            default:
                return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
        if (slotIn.getSlotType() == EquipmentSlotType.Group.HAND) {
            getHands().setStackInSlot(slotIn.getIndex(), stack);
        }
        else if (slotIn.getSlotType() == EquipmentSlotType.Group.ARMOR) {
            getArmor().setStackInSlot(slotIn.getIndex(), stack);
        }
    }

    @Override
    public HandSide getPrimaryHand() {
        return HandSide.RIGHT;
    }


    @Override
    public void readAdditional(CompoundNBT compound) {
        if(playerID == null)
        playerID = compound.getUniqueId("playerUUID");
        armorInventory.deserializeNBT(compound.getCompound("armorInv"));
        handsInventory.deserializeNBT(compound.getCompound("handInv"));
        mainInventory.deserializeNBT(compound.getCompound("mainInv"));
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.put("armorInv", armorInventory.serializeNBT());
        compound.put("handInv", handsInventory.serializeNBT());
        compound.put("mainInv", mainInventory.serializeNBT());
        compound.putUniqueId("playerUUID", getInternalID());
        super.writeAdditional(compound);
    }
}
