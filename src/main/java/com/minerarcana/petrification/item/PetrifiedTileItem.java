package com.minerarcana.petrification.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.registries.ForgeRegistries;

public class PetrifiedTileItem extends BlockItem {

    public PetrifiedTileItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        if (stack.hasTag() && stack.getTag() != null) {
            CompoundNBT nbt = stack.getTag();
            Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(nbt.getString("tileEntity")));
            if (block != null) {
                return new TranslationTextComponent(block.getTranslationKey());
            }
        }
        return super.getDisplayName(stack);
    }
}
