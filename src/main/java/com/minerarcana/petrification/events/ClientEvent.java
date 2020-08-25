package com.minerarcana.petrification.events;

import com.minerarcana.petrification.item.PetrifiedSplashPotion;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import net.minecraft.item.SplashPotionItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationItems.POTIONLIST;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public static void colorEvent(ColorHandlerEvent.Item event) {
        for(RegistryObject<Item> item: POTIONLIST) {
            if(item != null)
            event.getItemColors().register((stack, index)->
                    ((PetrifiedSplashPotion)item.get()).getColour(index),
                    item.get());
        }
    }


}
