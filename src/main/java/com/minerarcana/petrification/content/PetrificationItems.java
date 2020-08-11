package com.minerarcana.petrification.content;

import com.minerarcana.petrification.item.StoneEgg;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.Petrification.PG;

public class PetrificationItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[50];

    public static final RegistryObject<Item> STONETOMB_TOTEM = addSimpleItem(0,"stonetomb_totem",1);
    public static final RegistryObject<Item> STONE_FEATHER = addSimpleItem(1,"stone_feather",16);
    public static final RegistryObject<Item> STONE_EGG = ITEMLIST[2] = ITEMS.register("stone_egg", StoneEgg::new);

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }

    public static RegistryObject<Item> addSimpleItem(int index,String name, int stackSize) {
        return ITEMLIST[index] = ITEMS.register(name, () -> new Item(new Item.Properties().group(PG).maxStackSize(stackSize)));
    }

}
