package com.minerarcana.petrification.item;

import com.minerarcana.petrification.entities.RevivifyPotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

import java.util.function.Supplier;

import static com.minerarcana.petrification.content.PetrificationEffects.REVIVIFY;
import static com.minerarcana.petrification.content.PetrificationPotions.REVIVIFY_POTION;

public class RevivifiedSplashPotion extends PetrifiedSplashPotion {

    public RevivifiedSplashPotion(Supplier<Potion> potion) {
        super(potion);
    }

    @Override
    public PotionEntity getPotionEntity(World world, PlayerEntity player) {
        return new RevivifyPotionEntity(world, player);
    }
}
