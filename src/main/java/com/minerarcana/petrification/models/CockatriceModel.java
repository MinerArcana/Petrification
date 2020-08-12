package com.minerarcana.petrification.models;// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

import static com.minerarcana.petrification.Petrification.MOD_ID;

public class CockatriceModel extends AnimatedEntityModel<CockatriceEntity> {

    private final AnimatedModelRenderer body;
    private final AnimatedModelRenderer lwing;
    private final AnimatedModelRenderer rwing;
    private final AnimatedModelRenderer head;
    private final AnimatedModelRenderer beak1;
    private final AnimatedModelRenderer beak2;
    private final AnimatedModelRenderer tail;
    private final AnimatedModelRenderer tail2;
    private final AnimatedModelRenderer tail3;
    private final AnimatedModelRenderer lleg;
    private final AnimatedModelRenderer rleg;

    public CockatriceModel() {
        textureWidth = 48;
        textureHeight = 48;
        body = new AnimatedModelRenderer(this);
        body.setRotationPoint(0.0F, 16.0F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F, false);
        body.setModelRendererName("body");
        this.registerModelRenderer(body);

        lwing = new AnimatedModelRenderer(this);
        lwing.setRotationPoint(2.0F, -1.5F, 0.0F);
        body.addChild(lwing);
        setRotationAngle(lwing, -0.6981F, 0.0F, -0.7854F);
        lwing.setTextureOffset(14, 23).addBox(0.5F, 1.0F, 1.05F, 0.0F, 5.0F, 5.0F, 0.0F, false);
        lwing.setTextureOffset(6, 33).addBox(0.0F, 0.0F, -0.95F, 1.0F, 7.0F, 2.0F, 0.0F, false);
        lwing.setTextureOffset(29, 23).addBox(0.0F, 6.0F, 1.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        lwing.setModelRendererName("lwing");
        this.registerModelRenderer(lwing);

        rwing = new AnimatedModelRenderer(this);
        rwing.setRotationPoint(-2.0F, -1.5F, 0.0F);
        body.addChild(rwing);
        setRotationAngle(rwing, -0.6981F, 0.0F, 0.7854F);
        rwing.setTextureOffset(24, 24).addBox(-0.5F, 1.0F, 1.05F, 0.0F, 5.0F, 5.0F, 0.0F, false);
        rwing.setTextureOffset(0, 33).addBox(-1.0F, 0.0F, -0.95F, 1.0F, 7.0F, 2.0F, 0.0F, false);
        rwing.setTextureOffset(28, 17).addBox(-1.0F, 6.0F, 1.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
        rwing.setModelRendererName("rwing");
        this.registerModelRenderer(rwing);

        head = new AnimatedModelRenderer(this);
        head.setRotationPoint(0.0F, -1.0F, -4.0F);
        body.addChild(head);
        head.setTextureOffset(0, 24).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 3.0F, 0.0F, false);
        head.setTextureOffset(0, 0).addBox(0.0F, -9.0F, -1.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
        head.setModelRendererName("head");
        this.registerModelRenderer(head);

        beak1 = new AnimatedModelRenderer(this);
        beak1.setRotationPoint(0.0F, -3.0F, -2.0F);
        head.addChild(beak1);
        beak1.setTextureOffset(32, 0).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
        beak1.setModelRendererName("beak1");
        this.registerModelRenderer(beak1);

        beak2 = new AnimatedModelRenderer(this);
        beak2.setRotationPoint(0.0F, -3.0F, -2.0F);
        head.addChild(beak2);
        beak2.setTextureOffset(32, 4).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 2.0F, 0.0F, false);
        beak2.setTextureOffset(0, 0).addBox(-1.0F, 0.99F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
        beak2.setModelRendererName("beak2");
        this.registerModelRenderer(beak2);

        tail = new AnimatedModelRenderer(this);
        tail.setRotationPoint(0.0F, 0.0F, 4.0F);
        body.addChild(tail);
        tail.setTextureOffset(14, 18).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 6.0F, 0.0F, false);
        tail.setModelRendererName("tail");
        this.registerModelRenderer(tail);

        tail2 = new AnimatedModelRenderer(this);
        tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
        tail.addChild(tail2);
        tail2.setTextureOffset(22, 8).addBox(-1.5F, -1.5F, 0.5F, 3.0F, 3.0F, 6.0F, 0.0F, false);
        tail2.setModelRendererName("tail2");
        this.registerModelRenderer(tail2);

        tail3 = new AnimatedModelRenderer(this);
        tail3.setRotationPoint(0.0F, 0.0F, 6.5F);
        tail2.addChild(tail3);
        tail3.setTextureOffset(0, 14).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 8.0F, 0.0F, false);
        tail3.setModelRendererName("tail3");
        this.registerModelRenderer(tail3);

        lleg = new AnimatedModelRenderer(this);
        lleg.setRotationPoint(2.0F, 19.0F, 1.0F);
        lleg.setTextureOffset(31, 31).addBox(-2.0F, -0.1F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
        lleg.setModelRendererName("lleg");
        this.registerModelRenderer(lleg);

        rleg = new AnimatedModelRenderer(this);
        rleg.setRotationPoint(-1.0F, 19.0F, 1.0F);
        rleg.setTextureOffset(20, 0).addBox(-2.0F, -0.1F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
        rleg.setModelRendererName("rleg");
        this.registerModelRenderer(rleg);

        this.rootBones.add(body);
        this.rootBones.add(lleg);
        this.rootBones.add(rleg);
    }


    @Override
    public ResourceLocation getAnimationFileLocation() {
        return new ResourceLocation(MOD_ID, "models/animations/cockatrice_animations.json");
    }
}