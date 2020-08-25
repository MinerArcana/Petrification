package com.minerarcana.petrification.entities;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import static com.minerarcana.petrification.util.StaticMethodHandler.areaUnpetrification;

public class RevivifiedCloudEntity extends PetrificationCloudEntity {
    public RevivifiedCloudEntity(EntityType<? extends AreaEffectCloudEntity> cloud, World world) {
        super(cloud, world);
    }

    public RevivifiedCloudEntity(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void extraCloudStuff() {
        areaUnpetrification(world,getPosition(),3, 1);
    }
}
