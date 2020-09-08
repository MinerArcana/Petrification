package com.minerarcana.petrification.models;

import com.minerarcana.petrification.entities.PetrifiedPlayerEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;


public class NonRotatingBipedModel extends BipedModel<PetrifiedPlayerEntity> {
    public NonRotatingBipedModel(float modelSize) {
        super(modelSize, 0.0F, 64, 32);
    }

    @Override
    public void setRotationAngles(PetrifiedPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //Don't rotate
    }

    @Override
    public void setLivingAnimations(PetrifiedPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        //Don't animate
    }
}