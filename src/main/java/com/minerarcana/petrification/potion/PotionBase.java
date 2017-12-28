package com.minerarcana.petrification.potion;

import com.minerarcana.petrification.Petrification;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

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
        int k = 20 >> amplifier;
        return k <= 0 || duration % k == 0;
    }

    @Override
    public boolean isInstant() {
        return isInstant;
    }
}
