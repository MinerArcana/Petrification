package com.minerarcana.petrification.models;

import com.minerarcana.petrification.entities.PetrifiedPlayerEntity;
import net.minecraft.client.renderer.entity.model.PlayerModel;

//I feel like Alan wrote this mod Thanks Alan <3

public class PetrifiedPlayerModel extends PlayerModel<PetrifiedPlayerEntity> {
    public PetrifiedPlayerModel(float modelSize, boolean smallArmsIn) {
        super(modelSize, smallArmsIn);
    }

    @Override
    public void setLivingAnimations(PetrifiedPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        //Disable animations
    }

    @Override
    public void setRotationAngles(PetrifiedPlayerEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //Disable rotations
    }
}