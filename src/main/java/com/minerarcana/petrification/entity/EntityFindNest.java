package com.minerarcana.petrification.entity;

import net.minecraft.entity.ai.EntityAIBase;

public class EntityFindNest extends EntityAIBase {
    private EntityCockatrice entityCockatrice;

    public EntityFindNest(EntityCockatrice entityCockatrice) {
        this.entityCockatrice = entityCockatrice;
    }

    @Override
    public boolean shouldExecute() {
        return this.entityCockatrice.timeUntilNextEgg <= 0;
    }

    @Override
    public void startExecuting()
    {
    }
}
