package com.minerarcana.petrification.entity;

import com.leviathanstudio.craftstudio.CraftStudioApi;
import com.leviathanstudio.craftstudio.common.animation.AnimationHandler;
import com.leviathanstudio.craftstudio.common.animation.IAnimated;
import com.minerarcana.petrification.entity.ai.EntityAILayEgg;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.Optional;

import static com.minerarcana.petrification.Petrification.MODID;

public class EntityCockatrice extends EntityMobAnimated {
    
    public static final String NAME = "cockatrice";
    
    private int timeUntilNextEgg;
    private BlockPos nestPosition;

    private final static AnimationHandler<EntityCockatrice> ANIMATION_HANDLER = CraftStudioApi.getNewAnimationHandler(EntityCockatrice.class);

    private final static String POISONOUS_BREATH = "poisonous_breath";

    static {
        EntityCockatrice.ANIMATION_HANDLER.addAnim(MODID, POISONOUS_BREATH, NAME, false);
    }

    public EntityCockatrice(World worldIn) {
        super(worldIn);
        this.setSize(0.4F, 0.7F);
        this.setPathPriority(PathNodeType.OPEN, 0.0F);
        this.resetTimeUntilNextEgg();
    }
    
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 1.4D));
        this.tasks.addTask(2, new EntityAILayEgg(this));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        
        if(!this.onGround && this.motionY < 0.0D) {
            this.motionY *= 0.6D;
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (this.isWorldRemote()) {
            doBreathAttack();
        }
        return true;
    }


    public void doBreathAttack() {
        ANIMATION_HANDLER.startAnimation(MODID, POISONOUS_BREATH, this);
    }
    
    @Override
    protected boolean canDespawn() {
        return false;
    }
    
    @Override
    public void fall(float distance, float damageMultiplier) {
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }
    
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }
    
    public int getTimeUntilNextEgg() {
        return timeUntilNextEgg;
    }
    
    public Optional<BlockPos> getNestPosition() {
        return Optional.ofNullable(nestPosition);
    }
    
    public void setNestPosition(BlockPos nestPosition) {
        this.nestPosition = nestPosition;
    }
    
    public void breathAttack() {
    }
    
    public void resetTimeUntilNextEgg() {
        this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IAnimated> AnimationHandler<T> getAnimationHandler() {
        return (AnimationHandler<T>) ANIMATION_HANDLER;
    }
}
