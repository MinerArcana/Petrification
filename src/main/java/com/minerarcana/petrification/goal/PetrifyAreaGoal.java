package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static com.minerarcana.petrification.util.StaticMethodHandler.*;

public class PetrifyAreaGoal extends CockatriceGoal {

    private int animationTimer;
    public List<BlockPos> areaToPetrify = new ArrayList<>();

    public PetrifyAreaGoal(CockatriceEntity entity) {
        super(entity);
    }

    @Override
    public boolean shouldExecute() {
        return getCockatrice().getTimeUntilNextPetrification() >= 400;
    }

    @Override
    public void startExecuting() {
        getCockatrice().setAnimation((byte) 1);
        getCockatrice().setIsAngry(true);
        animationTimer = 0;
        areaToPetrify = getBlockPosToCheck(getCockatrice().getPosition());
    }

    @Override
    public boolean shouldContinueExecuting() {
        return animationTimer <= 40;
    }

    @Override
    public void resetTask() {
        super.resetTask();
    }

    @Override
    public void tick() {
        ++animationTimer;
        if (animationTimer >= 39) {
            if (!areaToPetrify.isEmpty()) {
                for (BlockPos pos : areaToPetrify) {
                    petrifyPos(getCockatrice().world, pos);
                }
            }
            getCockatrice().setTimeUntilNextPetrification(0);
            getCockatrice().setAnimation((byte) 0);
            getCockatrice().setIsAngry(false);
        } else if (animationTimer == 20) {
            entityAreaPetrification(getCockatrice().world, getCockatrice().getPosition());
        } else {
            if (!areaToPetrify.isEmpty()) {
                int x = 4;
                while (x > 0 && !areaToPetrify.isEmpty()) {
                    int rand = getCockatrice().world.rand.nextInt(areaToPetrify.size());
                    BlockPos pos = areaToPetrify.get(rand);
                    petrifyPos(getCockatrice().world, pos);
                    areaToPetrify.remove(rand);
                    --x;
                }
            }
        }
    }
}
