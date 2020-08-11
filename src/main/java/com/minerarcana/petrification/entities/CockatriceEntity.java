package com.minerarcana.petrification.entities;

import com.minerarcana.petrification.goal.LayEggGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CockatriceEntity extends MobEntity {

    private int timeUntilNextEgg;
    private boolean hasNest;
    private BlockPos nestPosition;

    public CockatriceEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LayEggGoal(this));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
    }

    public boolean hasNest() {
        return hasNest;
    }

    public BlockPos getNestPosition() {
        return nestPosition;
    }

    public int getTimeUntilNextEgg() {
        return timeUntilNextEgg;
    }

    public void setTimeUntilNextEgg(int timeUntilNextEgg) {
        this.timeUntilNextEgg = timeUntilNextEgg;
    }
}
