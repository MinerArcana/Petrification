package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;

import static com.minerarcana.petrification.blocks.StoneNestBlock.EGG;
import static com.minerarcana.petrification.content.PetrificationBlocks.STONE_NEST;
import static com.minerarcana.petrification.content.PetrificationItems.STONE_FEATHER;
import static java.lang.Boolean.TRUE;

public class LayEggGoal extends Goal {

    private final CockatriceEntity entity;
    private final PathNavigator navigator;
    private int timeToRecalcPath;
    private final int random;
    private final World world;

    public LayEggGoal(CockatriceEntity entity) {
        this.entity = entity;
        this.navigator = entity.getNavigator();
        this.random = entity.world.rand.nextInt(10);
        this.world = entity.world;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        if (entity.getAttackTarget() != null) {
            return false;
        } else return entity.getTimeUntilNextEgg() >= 4800;
    }

    @Override
    public void startExecuting() {
        this.timeToRecalcPath = 0;
        this.entity.setPathPriority(PathNodeType.WATER, 0.0F);
        if (random != 0 || world.getBlockState(entity.getNestPosition()).get(EGG)) {
            entity.setTimeUntilNextEgg(0);
            BlockPos pos = entity.getPosition();
            ItemStack stack = new ItemStack(STONE_FEATHER.get());
            if (world.rand.nextInt(100) == 0) {
                stack.setCount(3);
            } else {
                stack.setCount(1);
            }
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        if (this.navigator.noPath()) {
            return false;
        }
        return entity.getTimeUntilNextEgg() >= 4800;
    }

    @Override
    public void tick() {
        if (random == 0) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = 10;
                BlockPos pos = entity.getNestPosition();
                if (pos != null) {
                    if (entity.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) > 3) {
                        if (navigator.tryMoveToXYZ(pos.getX(), pos.getY(), pos.getZ() - 1, entity.getAIMoveSpeed())) {
                            world.setBlockState(pos, STONE_NEST.getBlock().getDefaultState().with(EGG, TRUE));
                            entity.setTimeUntilNextEgg(0);
                        }
                    }
                }
            }
        }
    }
}
