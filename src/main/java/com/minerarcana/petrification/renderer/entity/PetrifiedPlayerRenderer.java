package com.minerarcana.petrification.renderer.entity;

import com.minerarcana.petrification.entities.PetrifiedPlayerEntity;
import com.minerarcana.petrification.models.NonRotatingBipedModel;
import com.minerarcana.petrification.models.PetrifiedPlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.util.ResourceLocation;

public class PetrifiedPlayerRenderer extends LivingRenderer<PetrifiedPlayerEntity, PetrifiedPlayerModel> {

    public PetrifiedPlayerRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new PetrifiedPlayerModel(0F,true), .7F);
        this.addLayer(new BipedArmorLayer<>(this, new NonRotatingBipedModel(0.5F), new NonRotatingBipedModel(1.0F)));
        this.addLayer(new HeldItemLayer<>(this));
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new ElytraLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(PetrifiedPlayerEntity entity) {
        return entity.getPlayerInfo().getLocationSkin();
    }

}
