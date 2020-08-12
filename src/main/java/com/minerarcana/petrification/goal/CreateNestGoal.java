package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;

public class CreateNestGoal extends CockatriceGoal {

    public CreateNestGoal(CockatriceEntity entity) {
        super(entity);
    }

    @Override
    public boolean shouldExecute() {
        return !getCockatrice().hasNest() && getCockatrice().getNestPosition() == null;
    }

    @Override
    public void startExecuting() {
        getCockatrice().hasNest(true);
        getCockatrice().createRandomNest();
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !getCockatrice().hasNest() && getCockatrice().getNestPosition() == null;
    }
}
