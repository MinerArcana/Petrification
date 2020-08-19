package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.LivingEntity;

public class CatTargettingGoal extends CockatriceGoal {

    protected LivingEntity target;

    public CatTargettingGoal(CockatriceEntity entity) {
        super(entity);
    }

    @Override
    public boolean shouldExecute() {
        return false;
    }



}
