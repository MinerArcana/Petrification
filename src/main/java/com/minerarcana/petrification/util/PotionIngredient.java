package com.minerarcana.petrification.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.crafting.NBTIngredient;

public class PotionIngredient extends NBTIngredient {

        private PotionIngredient(Potion potion, ItemStack stack) {
            super(PotionUtils.addPotionToItemStack(stack, potion));
        }

        public static PotionIngredient asSplashPotion(Potion potion) {
            return new PotionIngredient(potion, new ItemStack(Items.SPLASH_POTION));
        }

        public static PotionIngredient asPotion(Potion potion) {
            return new PotionIngredient(potion, new ItemStack(Items.POTION));
        }
    }