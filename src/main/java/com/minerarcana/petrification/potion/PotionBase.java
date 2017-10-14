package com.minerarcana.petrification.potion;

import com.minerarcana.petrification.Petrification;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.minerarcana.petrification.Petrification.MODID;

public class PotionBase extends Potion {
    private static final ResourceLocation texture = new ResourceLocation(MODID, "textures/potion/potions.png");

    public PotionBase(String name, boolean isBadEffect, int liquidColor, int iconLocation) {
        super(isBadEffect, liquidColor);
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
}
