package com.minerarcana.petrification.util;

import com.minerarcana.petrification.entities.CockatriceEntity;
import com.minerarcana.petrification.tileentities.PetrifiedTile;
import com.minerarcana.petrification.tileentities.StatueTile;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;
import static com.minerarcana.petrification.content.PetrificationBlocks.PETRIFIED_TILE;
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

    public static void areaPetrification(World world, BlockPos pos){
        for(int x=-2;x<2;++x){
            for(int z=-2;z<2;++z){
                for(int y=-2;y<2;++y){
                    BlockPos newPos = pos.add(x,y,z);
                    if(!world.getBlockState(newPos).isAir()){
                        BlockState state = world.getBlockState(newPos);
                        Block block = state.getBlock();
                        TileEntity tile = world.getTileEntity(pos);
                        if(state.isIn(Tags.Blocks.DIRT)){
                            world.setBlockState(newPos, Blocks.COARSE_DIRT.getDefaultState());
                        } else if(state.isIn(Tags.Blocks.NETHERRACK) || block instanceof LeavesBlock || block instanceof TallGrassBlock || block.equals(Blocks.COARSE_DIRT)){
                            world.setBlockState(newPos, Blocks.GRAVEL.getDefaultState());
                        }else if(block.equals(Blocks.COBBLESTONE) || block instanceof RotatedPillarBlock && block.getRegistryName().toString().contains("log")){
                            world.setBlockState(newPos,Blocks.STONE_BRICK_WALL.getDefaultState());
                        } else if(block instanceof FenceBlock || block instanceof FenceGateBlock){
                            world.setBlockState(newPos,Blocks.STONE_BRICK_WALL.getDefaultState());
                        } else if(tile != null){
                            world.setBlockState(newPos,PETRIFIED_TILE.getBlock().getDefaultState());
                            PetrifiedTile pT = (PetrifiedTile) world.getTileEntity(newPos);
                            if(pT != null) {
                                pT.setTileEntity(tile);
                            }
                        }
                    }
                }
            }
        }
    }
}
