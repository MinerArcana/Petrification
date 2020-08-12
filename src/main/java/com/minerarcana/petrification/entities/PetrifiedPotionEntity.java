package com.minerarcana.petrification.entities;

import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static com.minerarcana.petrification.util.StaticMethodHandler.areaPetrification;

public class PetrifiedPotionEntity extends ProjectileItemEntity implements IRendersAsItem {

    public PetrifiedPotionEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public PetrifiedPotionEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public PetrifiedPotionEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    protected float getGravityVelocity() {
        return 0.05F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            ItemStack itemstack = this.getItem();
            Potion potion = PotionUtils.getPotionFromItem(itemstack);
            List<EffectInstance> list = PotionUtils.getEffectsFromStack(itemstack);
            if (this.isLingering()) {
                this.makeAreaOfEffectCloud(itemstack, potion);
            } else {
                this.applyEffect(list, result.getType() == RayTraceResult.Type.ENTITY ? ((EntityRayTraceResult)result).getEntity() : null);
            }
            areaPetrification(world,this.getPosition());
        }
    }

    private void makeAreaOfEffectCloud(ItemStack stack, Potion potion) {
        AreaEffectCloudEntity areaeffectcloudentity = new AreaEffectCloudEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ());
        Entity entity = this.func_234616_v_();
        if (entity instanceof LivingEntity) {
            areaeffectcloudentity.setOwner((LivingEntity)entity);
        }

        areaeffectcloudentity.setRadius(3.0F);
        areaeffectcloudentity.setRadiusOnUse(-0.5F);
        areaeffectcloudentity.setWaitTime(10);
        areaeffectcloudentity.setRadiusPerTick(-areaeffectcloudentity.getRadius() / (float)areaeffectcloudentity.getDuration());
        areaeffectcloudentity.setPotion(potion);

        for(EffectInstance effectinstance : PotionUtils.getFullEffectsFromItem(stack)) {
            areaeffectcloudentity.addEffect(new EffectInstance(effectinstance));
        }

        CompoundNBT compoundnbt = stack.getTag();
        if (compoundnbt != null && compoundnbt.contains("CustomPotionColor", 99)) {
            areaeffectcloudentity.setColor(compoundnbt.getInt("CustomPotionColor"));
        }

        this.world.addEntity(areaeffectcloudentity);
    }

    private void applyEffect(List<EffectInstance> effects, @Nullable Entity entity) {
        AxisAlignedBB axisalignedbb = this.getBoundingBox().grow(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, axisalignedbb);
        if (!list.isEmpty()) {
            for(LivingEntity livingentity : list) {
                if (livingentity.canBeHitWithPotion()) {
                    double d0 = this.getDistanceSq(livingentity);
                    if (d0 < 16.0D) {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
                        if (livingentity == entity) {
                            d1 = 1.0D;
                        }

                        for(EffectInstance effectinstance : effects) {
                            Effect effect = effectinstance.getPotion();
                            if (effect.isInstant()) {
                                effect.affectEntity(this, this.func_234616_v_(), livingentity, effectinstance.getAmplifier(), d1);
                            } else {
                                int i = (int)(d1 * (double)effectinstance.getDuration() + 0.5D);
                                if (i > 20) {
                                    livingentity.addPotionEffect(new EffectInstance(effect, i, effectinstance.getAmplifier(), effectinstance.isAmbient(), effectinstance.doesShowParticles()));
                                }
                            }
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
