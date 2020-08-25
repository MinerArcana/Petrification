package com.minerarcana.petrification.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import static com.minerarcana.petrification.content.PetrificationEffects.PETRIFICATION;

public class RevivifyEffect extends Effect {

    public RevivifyEffect() {
        super(EffectType.BENEFICIAL, 16711828);
    }

    @Override
    public void performEffect(LivingEntity entity, int amplifier) {
        if(entity.isPotionActive(PETRIFICATION.get())){
            entity.removePotionEffect(PETRIFICATION.get());
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return duration <= 20;
    }
}
