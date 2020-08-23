package com.minerarcana.petrification.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.petrification.Petrification.PG;
import static com.minerarcana.petrification.blocks.StoneNestBlock.EGG;
import static com.minerarcana.petrification.content.PetrificationBlocks.STONE_NEST;

public class StoneNestItem extends BlockItem {

    public StoneNestItem(Block block) {
        super(block,new Properties().group(PG).maxStackSize(1));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        ItemStack itemstack = player.getHeldItem(player.getActiveHand());
        if (!world.isRemote) {
            if(itemstack.hasTag() && itemstack.getTag() != null){
                CompoundNBT nbt = itemstack.getTag();
                if(nbt.getBoolean("egg")){
                    world.setBlockState(pos, STONE_NEST.get().getDefaultState().with(EGG,true));
                }else{
                    world.setBlockState(pos, STONE_NEST.get().getDefaultState().with(EGG,false));
                }
            }
        }
        return super.onItemUse(context);
    }

}
