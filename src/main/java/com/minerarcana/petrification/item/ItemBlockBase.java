package com.minerarcana.petrification.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.Objects;

public class ItemBlockBase extends ItemBlock {
    private int maxDamage;

    public ItemBlockBase(Block block) {
        super(block);
        Objects.requireNonNull(block.getRegistryName(), "Created ItemBlock for Block with Null Registry Name");
        this.setRegistryName(block.getRegistryName());
        maxDamage = this.block.getBlockState().getValidStates().size();
    }

    @Override
    public int getMetadata(int damage) {
        return damage > maxDamage ? 0 : damage;
    }
}
