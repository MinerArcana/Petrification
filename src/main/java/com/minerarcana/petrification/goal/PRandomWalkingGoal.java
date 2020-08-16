package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;

public class PRandomWalkingGoal extends WaterAvoidingRandomWalkingGoal {

    public PRandomWalkingGoal(CreatureEntity creature, double speedIn) {
        super(creature, speedIn);
    }

    public PRandomWalkingGoal(CreatureEntity creature, double speedIn, float probabilityIn) {
        super(creature, speedIn, probabilityIn);
    }

    protected CockatriceEntity getCockatrice(){
        return (CockatriceEntity) creature;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if(getCockatrice().isPetrifying()){
            return false;
        }
        return super.shouldContinueExecuting();
    }

    @Override
    public void startExecuting() {
        getCockatrice().setAnimation((byte)2);
        super.startExecuting();
    }

    @Override
    public void resetTask() {
        getCockatrice().setAnimation((byte)0);
        super.resetTask();
    }
}
