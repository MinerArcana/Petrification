package com.minerarcana.petrification.content;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.util.StaticPotionHandler.BASE_PETRIFICATION_INSTANCE;

public class PetrificationPotions {

    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MOD_ID);

    public static final RegistryObject<Potion> PETRIFICATION_POTION = POTIONS.register("simple_petrification", () -> new Potion(BASE_PETRIFICATION_INSTANCE.toArray(new EffectInstance[]{})));

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }

}