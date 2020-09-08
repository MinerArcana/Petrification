package com.minerarcana.petrification.content;

import com.minerarcana.petrification.entities.CockatriceEntity;
import com.minerarcana.petrification.entities.PetrifiedPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.Petrification.MOD_ID;

public class PetrificationEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITIES, MOD_ID);

    public static final RegistryObject<EntityType<CockatriceEntity>> COCKATRICE = ENTITY_TYPE.register("cockatrice",()-> EntityType.Builder.create(CockatriceEntity::new, EntityClassification.MONSTER).size(1,1).setShouldReceiveVelocityUpdates(false).build("cockatrice"));
    public static final RegistryObject<EntityType<PetrifiedPlayerEntity>> PETRIFIED_PLAYER = ENTITY_TYPE.register("petrified_player",()-> EntityType.Builder.create(PetrifiedPlayerEntity::new, EntityClassification.MISC).size(1.8F, .6F).setShouldReceiveVelocityUpdates(false).build("petrified_player"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }

}
