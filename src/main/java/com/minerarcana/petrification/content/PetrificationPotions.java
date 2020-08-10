package com.minerarcana.petrification.content;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static com.minerarcana.petrification.Petrification.MOD_ID;

import static com.minerarcana.petrification.util.StaticPotionHandler.LONG_PETRIFICATION_INSTANCE;
import static com.minerarcana.petrification.util.StaticPotionHandler.STRONG_PETRIFICATION_INSTANCE;

public class PetrificationPotions {

    private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, MOD_ID);

    public static final PotionRegistryGroup PETRIFICATION_BREW = new PotionRegistryGroup("petrification_potion", () -> new Potion(LONG_PETRIFICATION_INSTANCE.toArray(new EffectInstance[]{})), () -> Ingredient.fromItems(Items.GRAVEL))
            .setBase(() -> Potions.THICK)
            .addLongBrew(() -> new Potion(LONG_PETRIFICATION_INSTANCE.toArray(new EffectInstance[]{})))
            .addStrongBrew(() -> new Potion(STRONG_PETRIFICATION_INSTANCE.toArray(new EffectInstance[]{}))).register(POTIONS);

    public static void register(IEventBus modBus) {
        POTIONS.register(modBus);
    }

}
