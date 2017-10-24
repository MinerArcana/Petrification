package com.minerarcana.petrification.entity.ai;

import com.minerarcana.petrification.Petrification;
import com.minerarcana.petrification.block.BlockStoneNest;
import com.minerarcana.petrification.entity.EntityCockatrice;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.*;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import javax.annotation.Nullable;
import java.util.Optional;

public class EntityAILayEgg extends EntityAIBase {
    @ObjectHolder(Petrification.MODID + ":" + BlockStoneNest.NAME)
    private Block stoneNest;
    
    private EntityCockatrice entityCockatrice;
    
    public EntityAILayEgg(EntityCockatrice entityCockatrice) {
        this.entityCockatrice = entityCockatrice;
    }
    
    @Override
    public boolean shouldExecute() {
        return this.entityCockatrice.getTimeUntilNextEgg() <= 0;
    }
    
    @Override
    public void startExecuting() {
        World world = entityCockatrice.getEntityWorld();
        if(!entityCockatrice.getNestPosition().isPresent()) {
            IBlockState currentBlock = world.getBlockState(entityCockatrice.getPosition());
            if(currentBlock.getMaterial() == Material.ROCK) {
                BlockPos potentialNestPos = entityCockatrice.getPosition().up();
                IBlockState potentialNest = world.getBlockState(potentialNestPos);
                if(potentialNest.getBlock().isReplaceable(world, potentialNestPos)) {
                    world.setBlockState(potentialNestPos, stoneNest.getDefaultState().withProperty(BlockStoneNest.EGG, true));
                    entityCockatrice.setNestPosition(potentialNestPos);
                } else {
                    tryMove();
                }
            } else {
                entityCockatrice.breathAttack();
                tryMove();
            }
        } else {
            BlockPos nestPosition = entityCockatrice.getNestPosition().get();
            if (entityCockatrice.getPosition().equals(nestPosition)) {
                IBlockState stoneNestState = world.getBlockState(nestPosition);
                if (stoneNestState.getBlock() == stoneNest) {
                    world.setBlockState(nestPosition, stoneNestState.withProperty(BlockStoneNest.EGG, true));
                    entityCockatrice.resetTimeUntilNextEgg();
                }
            } else {
                navigate(entityCockatrice.getNavigator().getPathToPos(nestPosition));
            }
        }
    }
    
    private void navigate(@Nullable Path path) {
        entityCockatrice.getNavigator().setPath(path, 120);
    }
    
    private void tryMove() {
        Optional<Vec3d> move = Optional.ofNullable(RandomPositionGenerator.findRandomTarget(entityCockatrice, 10, 7));
        move.ifPresent(value -> navigate(entityCockatrice.getNavigator().getPathToXYZ(value.x, value.y, value.z)));
    }
}
