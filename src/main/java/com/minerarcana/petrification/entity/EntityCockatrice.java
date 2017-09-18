package com.minerarcana.petrification.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class EntityCockatrice extends EntityMob {
    public static final String NAME = "cockatrice";

    public EntityCockatrice(World worldIn) {
        super(worldIn);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }
}
