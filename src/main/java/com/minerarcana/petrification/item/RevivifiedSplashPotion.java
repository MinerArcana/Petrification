package com.minerarcana.petrification.item;

import com.minerarcana.petrification.entities.RevivifyPotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

import static com.minerarcana.petrification.content.PetrificationEffects.REVIVIFY;
import static com.minerarcana.petrification.content.PetrificationPotions.REVIVIFY_POTION;

public class RevivifiedSplashPotion extends PetrifiedSplashPotion {

    public RevivifiedSplashPotion(int amplifier) {
        super(amplifier);
    }

    @Override
    public Effect getEffect() {
        return REVIVIFY.get();
    }

    @Override
    public Potion getPotion() {
        return REVIVIFY_POTION.get();
    }

    @Override
    public PotionEntity getPotionEntity(World world, PlayerEntity player) {
        return new RevivifyPotionEntity(world, player);
    }
}
