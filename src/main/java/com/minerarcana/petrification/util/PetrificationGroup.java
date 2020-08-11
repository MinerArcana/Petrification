package com.minerarcana.petrification.util;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import javax.annotation.Nonnull;

public class PetrificationGroup extends ItemGroup {

    public PetrificationGroup() {
        super(ItemGroup.GROUPS.length, "petrification");
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return new ItemStack(Items.GRAVEL);
    }

}