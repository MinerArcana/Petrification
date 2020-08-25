package com.minerarcana.petrification.content;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.util.StaticPotionHandler.*;

public class PetrificationPotions {

    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MOD_ID);

    public static final RegistryObject<Potion> PETRIFICATION_POTION = POTIONS.register("petrification", () -> new Potion(PETRIFICATION_INSTANCE_0.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION1 = POTIONS.register("petrification_1", () -> new Potion(PETRIFICATION_INSTANCE_1.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION2 = POTIONS.register("petrification_2", () -> new Potion(PETRIFICATION_INSTANCE_2.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION3 = POTIONS.register("petrification_3", () -> new Potion(PETRIFICATION_INSTANCE_3.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION4 = POTIONS.register("petrification_4", () -> new Potion(PETRIFICATION_INSTANCE_4.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION5 = POTIONS.register("petrification_5", () -> new Potion(PETRIFICATION_INSTANCE_5.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION6 = POTIONS.register("petrification_6", () -> new Potion(PETRIFICATION_INSTANCE_6.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION7 = POTIONS.register("petrification_7", () -> new Potion(PETRIFICATION_INSTANCE_7.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION8 = POTIONS.register("petrification_8", () -> new Potion(PETRIFICATION_INSTANCE_8.toArray(new EffectInstance[]{})));
    public static final RegistryObject<Potion> PETRIFICATION_POTION9 = POTIONS.register("petrification_9", () -> new Potion(PETRIFICATION_INSTANCE_9.toArray(new EffectInstance[]{})));

    public static final RegistryObject<Potion> REVIVIFY_POTION = POTIONS.register("revivify", () -> new Potion(BASE_REVIVIFY_INSTANCE.toArray(new EffectInstance[]{})));

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }

}