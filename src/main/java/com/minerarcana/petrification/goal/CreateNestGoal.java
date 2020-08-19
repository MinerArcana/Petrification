package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.util.math.BlockPos;

public class CreateNestGoal extends CockatriceGoal {

    public CreateNestGoal(CockatriceEntity entity) {
        super(entity);
    }

    @Override
    public boolean shouldExecute() {
        return !getCockatrice().hasNest() && getCockatrice().getNestPosition() == BlockPos.ZERO;
    }

    @Override
    public void startExecuting() {
        getCockatrice().createOrFindRandomNest();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !getCockatrice().hasNest() || getCockatrice().getNestPosition() == BlockPos.ZERO;
    }
}
