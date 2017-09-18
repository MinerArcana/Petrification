package com.minerarcana.petrification.item;

import com.minerarcana.petrification.Petrification;
import com.minerarcana.petrification.block.BlockStoneNest;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import javax.annotation.Nonnull;

public class CreativeTabPetrification extends CreativeTabs {
    @ObjectHolder(Petrification.MODID + ":" + BlockStoneNest.NAME)
    private static Block stoneNest;

    public CreativeTabPetrification() {
        super("petrification");
    }

    @Override
    @Nonnull
    public ItemStack getTabIconItem() {
        return new ItemStack(stoneNest);
    }
}
