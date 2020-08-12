package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.entity.ai.goal.Goal;

public abstract class CockatriceGoal extends Goal {

    private final CockatriceEntity entity;

    public CockatriceGoal(CockatriceEntity entity) {
        this.entity = entity;
    }

    public CockatriceEntity getCockatrice() {
        return entity;
    }
}
