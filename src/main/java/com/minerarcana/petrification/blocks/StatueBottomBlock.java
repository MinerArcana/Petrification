package com.minerarcana.petrification.blocks;

import com.minerarcana.petrification.tileentities.StatueTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;

public class StatueBottomBlock extends Block {

    public StatueBottomBlock() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(1.5F,10F).sound(SoundType.STONE));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StatueTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof StatueTile){
            StatueTile statue = (StatueTile) tile;
            if(!statue.isPlayer()){
                Collection<ItemEntity> drops = statue.getInternalEntity().captureDrops(null);
                for(int x = 0; x<2;++x)
                drops.add(new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),Items.STONE.getDefaultInstance()));
                if (!net.minecraftforge.common.ForgeHooks.onLivingDrops(statue.getInternalEntity(), new DamageSource("statue"), drops, 1, true))
                    drops.forEach(world::addEntity);
            }else{

            }
        }
        super.onBlockHarvested(world, pos, state, player);
    }
}
