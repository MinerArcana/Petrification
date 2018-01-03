package com.minerarcana.petrification.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class PotionPetrification extends PotionBase {

    public PotionPetrification() {
        super("petrification", true, Color.GRAY.getRGB(), 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        PotionEffect effect = entityLivingBaseIn.getActivePotionEffect(this);
        if (effect != null && effect.getDuration() <= 1) {
            int nextAmplifier = amplifier + 1;
            entityLivingBaseIn.addPotionEffect(new PotionEffect(PotionBase.PETRIFICATION, nextAmplifier >= 10 ? Integer.MAX_VALUE : 20 * 60, amplifier + 1)); //TODO Change Duration time
        }
        entityLivingBaseIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, amplifier));
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
