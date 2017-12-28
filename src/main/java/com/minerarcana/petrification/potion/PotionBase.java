package com.minerarcana.petrification.potion;

import com.minerarcana.petrification.Petrification;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static com.minerarcana.petrification.Petrification.MODID;

public class PotionBase extends Potion {

    public static final Potion PETRIFICATION = new PotionBase("petrification", true, Color.GRAY.getRGB(), 0, false);
    public static final Potion REVIVIFY = new PotionBase("revivify", false, Color.WHITE.getRGB(), 1, true);

    private static final ResourceLocation texture = new ResourceLocation(MODID, "textures/potion/potions.png");

    private boolean isInstant;

    public PotionBase(String name, boolean isBadEffect, int liquidColor, int iconLocation, boolean isInstant) {
        super(isBadEffect, liquidColor);
        this.isInstant = isInstant;
        this.setPotionName("potion." + name);
        this.setRegistryName(new ResourceLocation(Petrification.MODID, name));
        this.setIconIndex(iconLocation % 8, iconLocation / 8);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        return super.getStatusIconIndex();
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean isInstant() {
        return isInstant;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if (this == PETRIFICATION){
            PotionEffect effect = entityLivingBaseIn.getActivePotionEffect(this);
            if (effect != null && effect.getDuration() <= 1){
                int nextAmplifier = amplifier +1;
                entityLivingBaseIn.addPotionEffect(new PotionEffect(PotionBase.PETRIFICATION, nextAmplifier >= 10 ? Integer.MAX_VALUE : 20*60, amplifier + 1)); //TODO Change Duration time
            }
            entityLivingBaseIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, amplifier));
        }
        if (this == REVIVIFY && entityLivingBaseIn.isPotionActive(PotionBase.REVIVIFY)){
            entityLivingBaseIn.removeActivePotionEffect(PotionBase.PETRIFICATION);
        }
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        if (this == PETRIFICATION) return Arrays.asList();
        return super.getCurativeItems();
    }
}
