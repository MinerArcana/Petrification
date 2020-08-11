package com.minerarcana.petrification.content;

import com.minerarcana.petrification.effects.PetrificationEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.Petrification.MOD_ID;


public class PetrificationEffects {

    private static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MOD_ID);

    public static final RegistryObject<Effect> PETRIFICATION = EFFECTS.register("petrification", () -> new PetrificationEffect().addAttributesModifier(Attributes.ARMOR,"fbfe1f18-9752-47ad-b586-c21e52eee2d3",1,AttributeModifier.Operation.ADDITION).addAttributesModifier(Attributes.MOVEMENT_SPEED,"3b3be5a8-b2f8-4823-a8c0-6499fe4a30d5",-0.1, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<Effect> REVIVIFY = EFFECTS.register("petrification", () -> new PetrificationEffect().addAttributesModifier(Attributes.ARMOR,"fbfe1f18-9752-47ad-b586-c21e52eee2d3",1,AttributeModifier.Operation.ADDITION).addAttributesModifier(Attributes.MOVEMENT_SPEED,"3b3be5a8-b2f8-4823-a8c0-6499fe4a30d5",-0.1, AttributeModifier.Operation.MULTIPLY_BASE));

    public static void register(IEventBus modBus) {
        EFFECTS.register(modBus);
    }
}
