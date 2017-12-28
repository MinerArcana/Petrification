package com.minerarcana.petrification.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectRevivify extends PotionEffect {
    public PotionEffectRevivify(Potion potionIn) {
        super(potionIn);
    }

    @Override
    public void performEffect(EntityLivingBase entityIn) {
        super.performEffect(entityIn);
        if (entityIn.isPotionActive(PotionBase.REVIVIFY)){
            entityIn.removeActivePotionEffect(PotionBase.PETRIFICATION);
        }
    }

}
