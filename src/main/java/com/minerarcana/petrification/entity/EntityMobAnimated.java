package com.minerarcana.petrification.entity;

import com.leviathanstudio.craftstudio.common.animation.IAnimated;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public abstract class EntityMobAnimated extends EntityMob implements IAnimated {
    public EntityMobAnimated(World world) {
        super(world);
    }

    @Override
    public int getDimension() {
        return this.getEntityWorld().provider.getDimension();
    }

    @Override
    public double getX() {
        return this.getPosition().getX();
    }

    @Override
    public double getY() {
        return this.getPosition().getY();
    }

    @Override
    public double getZ() {
        return this.getPosition().getZ();
    }

    @Override
    public boolean isWorldRemote() {
        return this.getEntityWorld().isRemote;
    }
}
