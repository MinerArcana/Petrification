package com.minerarcana.petrification.content;

import com.minerarcana.petrification.entities.CockatriceEntity;
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

    public static void register(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }

}
