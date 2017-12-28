package com.minerarcana.petrification.potion;

import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class PotionRevivify extends PotionBase {

    public PotionRevivify() {
        super("revivify", false, Color.WHITE.getRGB(), 1);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if (entityLivingBaseIn.isPotionActive(PotionBase.REVIVIFY)) {
            entityLivingBaseIn.removeActivePotionEffect(PotionBase.PETRIFICATION);
        }
    }
}
