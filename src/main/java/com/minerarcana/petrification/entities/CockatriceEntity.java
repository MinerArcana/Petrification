package com.minerarcana.petrification.entities;

import com.minerarcana.petrification.goal.*;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nullable;

import static com.minerarcana.petrification.content.PetrificationAnimations.*;
import static com.minerarcana.petrification.content.PetrificationBlocks.STONE_NEST;

public class CockatriceEntity extends CreatureEntity implements IAnimatedEntity {

    private EntityAnimationController moveController = new EntityAnimationController(this, "moveController", 10F, this::moveController);

    public EntityAnimationManager animationControllers = new EntityAnimationManager();

    private static final DataParameter<Boolean> IS_ANGRY;
    private static final DataParameter<BlockPos> NESTPOSITION;
    private static final DataParameter<Byte> PLAYING_ANIMATION;

    private int timeUntilNextEgg;
    private int timeUntilNextPetrification;
    private AnimationBuilder currentAnimation;

    public CockatriceEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    private <E extends Entity> boolean moveController(AnimationTestEvent<E> entityAnimationTestEvent) {
        moveController.transitionLengthTicks = 10;
        moveController.setAnimation(getAnimationBuilder());
        return true;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LayEggGoal(this));
        this.goalSelector.addGoal(2, new CreateNestGoal(this));
        this.goalSelector.addGoal(6, new PetrifyAreaGoal(this));
        this.goalSelector.addGoal(7,new ReturnToNestGoal(this));
        this.goalSelector.addGoal(8, new PRandomWalkingGoal(this, 1.0D,300));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(IS_ANGRY, false);
        this.dataManager.register(NESTPOSITION, BlockPos.ZERO);
        this.dataManager.register(PLAYING_ANIMATION, (byte) 0);
    }

    static {
        IS_ANGRY = EntityDataManager.createKey(CockatriceEntity.class, DataSerializers.BOOLEAN);
        NESTPOSITION = EntityDataManager.createKey(CockatriceEntity.class, DataSerializers.BLOCK_POS);
        PLAYING_ANIMATION = EntityDataManager.createKey(CockatriceEntity.class, DataSerializers.BYTE);
    }

    @Override
    public int getAir() {
        return super.getAir();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        while(super.isEntityInsideOpaqueBlock()){
            setPosition(getPosX(),getPosY() + 1,getPosZ());
        }
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if(hasNest()) {
            ++timeUntilNextEgg;
            ++timeUntilNextPetrification;
        }
        animationController();
    }

    public boolean hasNest() {
        return world.getBlockState(getNestPosition()).getBlock().equals(STONE_NEST.get());
    }

    public boolean isAngry() {
        return dataManager.get(IS_ANGRY);
    }

    public boolean isIdling() {
        return dataManager.get(PLAYING_ANIMATION) == 0;
    }

    public boolean isWalking(){return dataManager.get(PLAYING_ANIMATION) == 2;}

    public boolean isPetrifying() {
        return dataManager.get(PLAYING_ANIMATION) == 1;
    }

    public void setIsAngry(boolean anger) {
        dataManager.set(IS_ANGRY, anger);
    }

    public BlockPos getNestPosition() {
        return dataManager.get(NESTPOSITION);
    }

    public void setNestPosition(BlockPos pos){
        dataManager.set(NESTPOSITION, pos);
    }

    public int getTimeUntilNextEgg() {
        return timeUntilNextEgg;
    }

    public int getTimeUntilNextPetrification() {
        return timeUntilNextPetrification;
    }

    public void setTimeUntilNextEgg(int timeUntilNextEgg) {
        this.timeUntilNextEgg = timeUntilNextEgg;
    }

    public void setTimeUntilNextPetrification(int timeUntilNextPetrification) {
        this.timeUntilNextPetrification = timeUntilNextPetrification;
    }

    private AnimationBuilder getAnimationBuilder() {
        if (currentAnimation == null) {
            return IDLE1;
        }
        return currentAnimation;
    }

    public void setAnimation(byte data){
        dataManager.set(PLAYING_ANIMATION,data);
    }

    private void animationController(){
        if(isIdling()){
            setCurrentAnimation(IDLE1);
        } else if(isPetrifying()){
            setCurrentAnimation(PETRIFY);
        }else if(dataManager.get(PLAYING_ANIMATION) == 2){
            setCurrentAnimation(WALK);
        }else if(dataManager.get(PLAYING_ANIMATION) == 3){
            setCurrentAnimation(LAYEGG);
        }
    }

    private void setCurrentAnimation(AnimationBuilder currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    private void registerAnimationControllers() {
        if (world.isRemote) {
            this.getAnimationManager().addAnimationController(moveController);
        }
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected int calculateFallDamage(float distance, float damageMultiplier) {
        return 0;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return animationControllers;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void createRandomNest() {
        int x = rand.nextInt(4);
        int y = rand.nextInt(4);
        int z = rand.nextInt(4);
        BlockPos pos;
        if (x == 0 && z == 0) {
            z = 1;
            x = -1;
        }
        if (y == 0) {
            pos = getPosition().add(x,0,z);
        } else if (y == 1) {
            pos = getPosition().add(x,0,-z);
        } else if (y == 2){
            pos = getPosition().add(-x,0,-z);
        }else{
            pos = getPosition().add(-x,0,z);
        }
        if(world.getBlockState(pos).getMaterial().isReplaceable() && !world.getBlockState(pos.down()).getMaterial().isReplaceable()){
            world.setBlockState(pos,STONE_NEST.get().getDefaultState());
            world.setBlockState(pos.down(), Blocks.STONE.getDefaultState());
            setNestPosition(pos);
        }else{
            if(world.getBlockState(pos.down()).getMaterial().isReplaceable() && !world.getBlockState(pos.down(2)).getMaterial().isReplaceable()){
                world.setBlockState(pos.down(),STONE_NEST.get().getDefaultState());
                world.setBlockState(pos.down(2), Blocks.STONE.getDefaultState());
                setNestPosition(pos.down());
            }else{
                if(world.getBlockState(pos.up()).getMaterial().isReplaceable() && !world.getBlockState(pos).getMaterial().isReplaceable()){
                    world.setBlockState(pos.up(),STONE_NEST.get().getDefaultState());
                    world.setBlockState(pos, Blocks.STONE.getDefaultState());
                    setNestPosition(pos.up());
                }
            }
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        setIsAngry(compound.getBoolean("anger"));
        setNestPosition(BlockPos.fromLong(compound.getLong("nestPos")));
        super.read(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        compound.putBoolean("anger", isAngry());
        compound.putLong("nestPos", getNestPosition().toLong());
        super.writeAdditional(compound);
    }
}
