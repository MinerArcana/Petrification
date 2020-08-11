package com.minerarcana.petrification.util;

import com.minerarcana.petrification.entities.CockatriceEntity;
import com.minerarcana.petrification.tileentities.StatueTile;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;
import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;

public class StaticMethodHandler {

    public static void setTombStatue(LivingEntity entity){
        BlockPos pos = entity.getPosition();
        World world = entity.world;
        world.setBlockState(pos,ENTITY_STATUE.getBlock().getDefaultState());
        if(world.getTileEntity(pos) != null){
            StatueTile tile = (StatueTile) world.getTileEntity(pos);
            tile.setEntity(entity);
        }
    }

    public static void spawnCockatrice(World world, BlockPos pos) {
        CockatriceEntity entityCockatrice = COCKATRICE.get().create(world);
        entityCockatrice.setPosition(pos.getX(), pos.getY(), pos.getZ());
        if (!world.isRemote) {
            world.addEntity(entityCockatrice);
        }
    }

}
