package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;

import java.util.EnumSet;

public class CockatriceAttackGoal extends CockatriceGoal {

    private final double speedTowardsTarget;
    private Path path;
    private int attackDelay;
    private double targetX;
    private double targetY;
    private double targetZ;
    private long gameTime;
    private boolean longMemory;
    private int delayCounter;

    public CockatriceAttackGoal(CockatriceEntity creature) {
        super(creature);
        this.speedTowardsTarget = 1D;
        this.longMemory = true;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        long i = getCockatrice().world.getGameTime();
        if (i - this.gameTime < 20L) {
            return false;
        } else {
            this.gameTime = i;
            LivingEntity livingentity = getCockatrice().getAttackTarget();
            if (livingentity == null || getCockatrice().getNearbyCatTarget() != null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                this.path = getCockatrice().getNavigator().getPathToEntity(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else {
                    return this.getAttackReachSqr(livingentity) >= getCockatrice().getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
                }
            }
        }
    }

    @Override
    public void startExecuting() {
        this.getCockatrice().getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.getCockatrice().setAggroed(true);
        this.getCockatrice().setAnimation((byte) 4);
        this.delayCounter = 0;
        this.attackDelay = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        LivingEntity livingentity = this.getCockatrice().getAttackTarget();
        if (livingentity == null || getCockatrice().getNearbyCatTarget() != null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.longMemory) {
            return !this.getCockatrice().getNavigator().noPath();
        } else if (!this.getCockatrice().isWithinHomeDistanceFromPosition(livingentity.getPosition())) {
            return false;
        } else {
            return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity)livingentity).isCreative();
        }
    }

    public void tick() {
        LivingEntity livingentity = getCockatrice().getAttackTarget();
        getCockatrice().getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
        double d0 = getCockatrice().getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
        this.delayCounter = Math.max(this.delayCounter - 1, 0);
        if ((this.longMemory || getCockatrice().getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || getCockatrice().getRNG().nextFloat() < 0.05F)) {
            this.targetX = livingentity.getPosX();
            this.targetY = livingentity.getPosY();
            this.targetZ = livingentity.getPosZ();
            this.delayCounter = 4 + getCockatrice().getRNG().nextInt(7);
            if (d0 > 1024.0D) {
                this.delayCounter += 10;
            } else if (d0 > 256.0D) {
                this.delayCounter += 5;
            }
            if (!getCockatrice().getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
                this.delayCounter += 15;
            }
        }

        this.attackDelay = Math.max(this.attackDelay - 1, 0);
        this.checkAndPerformAttack(livingentity, d0);
    }

    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackDelay <= 0) {
            this.delayReset();
            getCockatrice().attackEntityAsMob(enemy);
            if(getCockatrice().world.rand.nextInt(100) <= 15){
                getCockatrice().petrifyEnemy(enemy);
            }
        }
    }

    public void resetTask() {
        LivingEntity livingentity = this.getCockatrice().getAttackTarget();
        if (!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
            this.getCockatrice().setAttackTarget((LivingEntity)null);
        }
        this.getCockatrice().setAggroed(false);
        this.getCockatrice().getNavigator().clearPath();
        this.getCockatrice().setAnimation((byte) 0);
    }

    private void delayReset(){
        this.attackDelay = 20;
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        return (4 + attackTarget.getWidth());
    }
}
