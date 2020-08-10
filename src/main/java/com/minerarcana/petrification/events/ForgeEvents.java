package com.minerarcana.petrification.events;

import com.minerarcana.petrification.tileentities.StatueTile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationBlocks.ENTITY_STATUE;
import static com.minerarcana.petrification.content.PetrificationEffects.PETRIFICATION;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void livingJumpEvent(LivingEvent.LivingJumpEvent event) {
        if (event.getEntityLiving().isPotionActive(PETRIFICATION.get())) {
            LivingEntity entity = event.getEntityLiving();
            double y = entity.getMotion().y * ((float) (entity.getActivePotionEffect(PETRIFICATION.get()).getAmplifier()+1) / 10);
            entity.setMotion(entity.getMotion().x, entity.getMotion().y - y, entity.getMotion().z);
        }
    }

    @SubscribeEvent
    public static void potionExpireEvent(PotionEvent.PotionExpiryEvent event) {
        EffectInstance eI = event.getPotionEffect();
        if(eI.getPotion().equals(PETRIFICATION.get())){
            if(eI.getEffectInstance().getAmplifier() >=9){
                LivingEntity entity = event.getEntityLiving();
                BlockPos pos = entity.getPosition();
                entity.onDeath(new DamageSource("petrification"));
                World world = entity.world;
                world.setBlockState(pos,ENTITY_STATUE.getBlock().getDefaultState());
                if(world.getTileEntity(pos) != null){
                    StatueTile tile = (StatueTile) world.getTileEntity(pos);
                    tile.setEntity(entity);
                }
            }
        }

    }



}
