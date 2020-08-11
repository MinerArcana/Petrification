package com.minerarcana.petrification.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;
import static com.minerarcana.petrification.content.PetrificationItems.STONE_EGG;
import static com.minerarcana.petrification.util.StaticMethodHandler.spawnCockatrice;

public class StoneEggEntity extends ProjectileItemEntity {

    public StoneEggEntity(EntityType<? extends EggEntity> p_i50154_1_, World p_i50154_2_) {
        super(p_i50154_1_, p_i50154_2_);
    }

    public StoneEggEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityType.EGG, throwerIn, worldIn);
    }

    public StoneEggEntity(World worldIn, double x, double y, double z) {
        super(EntityType.EGG, x, y, z, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            double d0 = 0.08D;

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, this.getItem()), this.getPosX(), this.getPosY(), this.getPosZ(), ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }

    @Override
    protected Item getDefaultItem() {
        return STONE_EGG.get();
    }


    protected void onEntityHit(EntityRayTraceResult rayTraceResult) {
        super.onEntityHit(rayTraceResult);
        rayTraceResult.getEntity().attackEntityFrom(DamageSource.causeThrownDamage(this, this.func_234616_v_()), 3.0F);
    }

    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            if (this.rand.nextInt(8) == 0) {
                int i = 1;
                if (this.rand.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {
                    spawnCockatrice(world,getPosition());
                }
            }

            this.world.setEntityState(this, (byte)3);
            this.remove();
        }

    }
}
