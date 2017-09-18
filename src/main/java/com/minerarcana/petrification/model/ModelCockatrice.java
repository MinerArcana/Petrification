package com.minerarcana.petrification.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ModelCockatrice extends ModelBase {
    public ModelRenderer beak;
    public ModelRenderer legRight;
    public ModelRenderer rightWing1;
    public ModelRenderer body;
    public ModelRenderer legLeft;
    public ModelRenderer tail1;
    public ModelRenderer leftWing1;
    public ModelRenderer head;
    public ModelRenderer crest;
    public ModelRenderer dewlap;
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;
    public ModelRenderer tail5;
    public ModelRenderer tail4;
    public ModelRenderer tail3;
    public ModelRenderer tail2;

    public ModelCockatrice() {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.beak = new ModelRenderer(this, 14, 0);
        this.beak.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.beak.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2);
        this.legRight = new ModelRenderer(this, 26, 0);
        this.legRight.setRotationPoint(1.0F, 19.0F, 1.0F);
        this.legRight.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.rightWing1 = new ModelRenderer(this, 48, 20);
        this.rightWing1.setRotationPoint(3.0F, 13.0F, -3.0F);
        this.rightWing1.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6);
        this.setRotationAngles(this.rightWing1, 0.0F, -0.2546435405291338F, -1.3962634015954636F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 16.0F, 0.0F);
        this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6);
        this.setRotationAngles(this.body, 1.5707963267948966F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 26, 0);
        this.legLeft.setRotationPoint(-2.0F, 19.0F, 1.0F);
        this.legLeft.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
        this.tail1 = new ModelRenderer(this, 27, 24);
        this.tail1.setRotationPoint(0.0F, 14.0F, 4.0F);
        this.tail1.addBox(-2.0F, 0.0F, 0.0F, 4, 4, 3);
        this.leftWing1 = new ModelRenderer(this, 48, 20);
        this.leftWing1.setRotationPoint(-3.0F, 13.0F, -3.0F);
        this.leftWing1.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 6);
        this.setRotationAngles(this.leftWing1, 0.0F, 0.2530727415391778F, 1.3962634015954636F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3);
        this.crest = new ModelRenderer(this, 41, 3);
        this.crest.setRotationPoint(-0.5F, 9.1F, -6.0F);
        this.crest.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3);
        this.setRotationAngles(this.crest, 0.6368706733475028F, 0.0F, 0.0F);
        this.dewlap = new ModelRenderer(this, 14, 4);
        this.dewlap.setRotationPoint(0.0F, 15.0F, -4.0F);
        this.dewlap.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2);
        this.leftWing = new ModelRenderer(this, 24, 13);
        this.leftWing.setRotationPoint(-6.9224195F, 13.789937F, -3.025038F);
        this.leftWing.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 6);
        this.setRotationAngles(this.leftWing, 0.6632251157578453F, 0.2530727415391778F, 1.3962634015954636F);
        this.rightWing = new ModelRenderer(this, 24, 13);
        this.rightWing.setRotationPoint(6.922426F, 13.789898F, -3.02519F);
        this.rightWing.addBox(0.0F, 0.0F, 0.0F, 1, 4, 6);
        this.setRotationAngles(this.rightWing, 0.6632251157578453F, -0.2546435571738906F, -1.3962634015954636F);
        this.tail5 = new ModelRenderer(this, 38, 9);
        this.tail5.setRotationPoint(0.0F, 15.0F, 16.0F);
        this.tail5.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 8);
        this.tail4 = new ModelRenderer(this, 51, 2);
        this.tail4.setRotationPoint(0.0F, 17.5F, 13.0F);
        this.tail4.addBox(-1.5F, -3.0F, 0.0F, 3, 3, 3);
        this.tail3 = new ModelRenderer(this, 51, 2);
        this.tail3.setRotationPoint(0.0F, 14.5F, 10.0F);
        this.tail3.addBox(-1.5F, 0.0F, 0.0F, 3, 3, 3);
        this.tail2 = new ModelRenderer(this, 27, 24);
        this.tail2.setRotationPoint(0.0F, 18.0F, 7.0F);
        this.tail2.addBox(-2.0F, -4.0F, 0.0F, 4, 4, 3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale) {
        this.beak.render(scale);
        this.legRight.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightWing1.offsetX, this.rightWing1.offsetY, this.rightWing1.offsetZ);
        GlStateManager.translate(this.rightWing1.rotationPointX * scale, this.rightWing1.rotationPointY * scale, this.rightWing1.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.rightWing1.offsetX, -this.rightWing1.offsetY, -this.rightWing1.offsetZ);
        GlStateManager.translate(-this.rightWing1.rotationPointX * scale, -this.rightWing1.rotationPointY * scale, -this.rightWing1.rotationPointZ * scale);
        this.rightWing1.render(scale);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body.offsetX, this.body.offsetY, this.body.offsetZ);
        GlStateManager.translate(this.body.rotationPointX * scale, this.body.rotationPointY * scale, this.body.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.body.offsetX, -this.body.offsetY, -this.body.offsetZ);
        GlStateManager.translate(-this.body.rotationPointX * scale, -this.body.rotationPointY * scale, -this.body.rotationPointZ * scale);
        this.body.render(scale);
        GlStateManager.popMatrix();
        this.legLeft.render(scale);
        this.tail1.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftWing1.offsetX, this.leftWing1.offsetY, this.leftWing1.offsetZ);
        GlStateManager.translate(this.leftWing1.rotationPointX * scale, this.leftWing1.rotationPointY * scale, this.leftWing1.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.leftWing1.offsetX, -this.leftWing1.offsetY, -this.leftWing1.offsetZ);
        GlStateManager.translate(-this.leftWing1.rotationPointX * scale, -this.leftWing1.rotationPointY * scale, -this.leftWing1.rotationPointZ * scale);
        this.leftWing1.render(scale);
        GlStateManager.popMatrix();
        this.head.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.crest.offsetX, this.crest.offsetY, this.crest.offsetZ);
        GlStateManager.translate(this.crest.rotationPointX * scale, this.crest.rotationPointY * scale, this.crest.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 0.9F);
        GlStateManager.translate(-this.crest.offsetX, -this.crest.offsetY, -this.crest.offsetZ);
        GlStateManager.translate(-this.crest.rotationPointX * scale, -this.crest.rotationPointY * scale, -this.crest.rotationPointZ * scale);
        this.crest.render(scale);
        GlStateManager.popMatrix();
        this.dewlap.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftWing.offsetX, this.leftWing.offsetY, this.leftWing.offsetZ);
        GlStateManager.translate(this.leftWing.rotationPointX * scale, this.leftWing.rotationPointY * scale, this.leftWing.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.leftWing.offsetX, -this.leftWing.offsetY, -this.leftWing.offsetZ);
        GlStateManager.translate(-this.leftWing.rotationPointX * scale, -this.leftWing.rotationPointY * scale, -this.leftWing.rotationPointZ * scale);
        this.leftWing.render(scale);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightWing.offsetX, this.rightWing.offsetY, this.rightWing.offsetZ);
        GlStateManager.translate(this.rightWing.rotationPointX * scale, this.rightWing.rotationPointY * scale, this.rightWing.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.rightWing.offsetX, -this.rightWing.offsetY, -this.rightWing.offsetZ);
        GlStateManager.translate(-this.rightWing.rotationPointX * scale, -this.rightWing.rotationPointY * scale, -this.rightWing.rotationPointZ * scale);
        this.rightWing.render(scale);
        GlStateManager.popMatrix();
        this.tail5.render(scale);
        this.tail4.render(scale);
        this.tail3.render(scale);
        this.tail2.render(scale);
    }

    private void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
