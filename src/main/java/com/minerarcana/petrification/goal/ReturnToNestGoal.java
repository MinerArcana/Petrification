package com.minerarcana.petrification.goal;

import com.minerarcana.petrification.entities.CockatriceEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ReturnToNestGoal extends CockatriceGoal {

    private int animationDelay;
    private BlockPos nest;

    public ReturnToNestGoal(CockatriceEntity entity) {
        super(entity);
        animationDelay = 0;
    }

    @Override
    public boolean shouldExecute() {
        if(!getCockatrice().hasNest() || getCockatrice().getNestPosition().equals(BlockPos.ZERO)){
            return false;
        }
        if(getCockatrice().getNearbyCatTarget() != null || getCockatrice().getAttackTarget() != null){
           return false;
        }
        nest = getCockatrice().getNestPosition();
        return getCockatrice().getDistanceSq(nest.getX(),nest.getY(),nest.getZ()) > 60 && getCockatrice().world.rand.nextInt(30) == 0 && getCockatrice().isIdling();
    }

    @Override
    public void startExecuting() {
        getCockatrice().setAnimation((byte)2);
        animationDelay = 0;
    }

    @Override
    public boolean shouldContinueExecuting() {
        return !this.getCockatrice().getNavigator().noPath() || animationDelay < 11;
    }

    @Override
    public void resetTask() {
        getCockatrice().setAnimation((byte) 0);
        getCockatrice().getNavigator().clearPath();
    }

    @Override
    public void tick() {
        animationDelay++;
        if(animationDelay == 10){
            World world = getCockatrice().world;
            Random random = getCockatrice().world.rand;
            int x = random.nextInt(2);
            int y = random.nextInt(4);
            int z = random.nextInt(2);
            if(x == 0 && z == 0){
                x = -1;
                z = -2;
            }
            if(y == 0){
                x = -x;
            } else if(y == 1){
                z = -z;
                x = -x;
            } else if(y == 2){
                z = -z;
            }
            BlockPos pos = nest.add(x,0,z);
            if(!world.getBlockState(pos).isAir()){
                if(world.getBlockState(pos.up()).isAir()){
                    pos = pos.up();
                }
            }
            getCockatrice().getNavigator().tryMoveToXYZ(pos.getX(),pos.getY()+ 1,pos.getZ(), 1);
        }
    }
}
