package com.minerarcana.petrification.entities;

import com.minerarcana.petrification.item.RevivifiedSplashPotion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.petrification.util.StaticMethodHandler.areaUnpetrification;

public class RevivifyPotionEntity extends PotionEntity {

    public RevivifyPotionEntity(EntityType<? extends PotionEntity> typeIn, World worldIn) {
        super(typeIn, worldIn);
    }

    public RevivifyPotionEntity(World worldIn, LivingEntity livingEntityIn) {
        super(worldIn, livingEntityIn);
    }

    public RevivifyPotionEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            ItemStack itemstack = this.getItem();
            if (itemstack.getItem() instanceof RevivifiedSplashPotion) {
                EffectInstance effect = ((RevivifiedSplashPotion) itemstack.getItem()).getPotionEffect().get();
                if (this.isLingering()) {
                    this.makeAreaOfEffectCloud(itemstack, effect);
                } else {
                    this.applyEffect(effect, result.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult) result).getEntity() : null);
                    areaUnpetrification();
                }
            }
        }
    }

    private void makeAreaOfEffectCloud(ItemStack stack, EffectInstance effect) {
        PetrificationCloudEntity areaeffectcloudentity = new PetrificationCloudEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ());
        Entity entity = this.func_234616_v_();
        if (entity instanceof LivingEntity) {
            areaeffectcloudentity.setOwner((LivingEntity) entity);
        }

        areaeffectcloudentity.setRadius(3.0F);
        areaeffectcloudentity.setRadiusOnUse(-0.5F);
        areaeffectcloudentity.setWaitTime(10);
        areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float) areaeffectcloudentity.getDuration());
        areaeffectcloudentity.addEffect(effect);

        CompoundNBT compoundnbt = stack.getTag();
        if (compoundnbt != null && compoundnbt.contains("CustomPotionColor", 99)) {
            areaeffectcloudentity.setColor(compoundnbt.getInt("CustomPotionColor"));
        }
        this.world.addEntity(areaeffectcloudentity);
    }

    private void applyEffect(EffectInstance effectInstance, @Nullable Entity entity) {
        AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for (LivingEntity livingentity : list) {
                if (livingentity.canBeHitWithPotion()) {
                    double d0 = this.getDistanceSq(livingentity);
                    if (d0 < 16.0D) {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                        if (livingentity == entity) {
                            d1 = 1.0D;
                        }
                        Effect effect = effectInstance.getPotion();
                        int i = (int) (d1 * (double) effectInstance.getDuration() + 0.5D);
                        if (i > 20) {
                            livingentity.addPotionEffect(new EffectInstance(effect, i, effectInstance.getAmplifier(), effectInstance.isAmbient(), effectInstance.doesShowParticles()));
                        }
                    }
                }
            }
        }
    }

    private boolean isLingering() {
        return this.getItem().getItem() == Items.LINGERING_POTION;
    }
}
