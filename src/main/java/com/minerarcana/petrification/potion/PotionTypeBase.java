package com.minerarcana.petrification.potion;

import com.minerarcana.petrification.Petrification;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

public class PotionTypeBase extends PotionType {
    public PotionTypeBase(String name, PotionEffect... potionEffects) {
        super(name, potionEffects);
        this.setRegistryName(new ResourceLocation(Petrification.MODID, name));
    }
}
