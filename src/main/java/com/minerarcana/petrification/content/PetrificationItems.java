package com.minerarcana.petrification.content;

import com.minerarcana.petrification.item.PetrifiedSplashPotion;
import com.minerarcana.petrification.item.RevivifiedSplashPotion;
import com.minerarcana.petrification.item.StoneEgg;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.Petrification.PG;
import static com.minerarcana.petrification.content.PetrificationPotions.*;

public class PetrificationItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item>[] ITEMLIST = new RegistryObject[50];
    public static final RegistryObject<Item>[] POTIONLIST = new RegistryObject[50];

    public static final RegistryObject<Item> STONETOMB_TOTEM = addSimpleItem(0, "stonetomb_totem", 1);
    public static final RegistryObject<Item> STONE_FEATHER = addSimpleItem(1, "stone_feather", 16);
    public static final RegistryObject<Item> STONE_EGG = ITEMLIST[2] = ITEMS.register("stone_egg", StoneEgg::new);

    public static final RegistryObject<Item> REVIVIFY_SPLASH = POTIONLIST[49] = ITEMS.register("revivify_splash_potion", () -> new RevivifiedSplashPotion(REVIVIFY_POTION));

    public static final RegistryObject<Item> PETRIFIED_SPLASH = addPetrificationSplashPotion(0,PETRIFICATION_POTION);
    public static final RegistryObject<Item> PETRIFIED_SPLASH1 = addPetrificationSplashPotion(1,PETRIFICATION_POTION1);
    public static final RegistryObject<Item> PETRIFIED_SPLASH2 = addPetrificationSplashPotion(2,PETRIFICATION_POTION2);
    public static final RegistryObject<Item> PETRIFIED_SPLASH3 = addPetrificationSplashPotion(3,PETRIFICATION_POTION3);
    public static final RegistryObject<Item> PETRIFIED_SPLASH4 = addPetrificationSplashPotion(4,PETRIFICATION_POTION4);
    public static final RegistryObject<Item> PETRIFIED_SPLASH5 = addPetrificationSplashPotion(5,PETRIFICATION_POTION5);
    public static final RegistryObject<Item> PETRIFIED_SPLASH6 = addPetrificationSplashPotion(6,PETRIFICATION_POTION6);
    public static final RegistryObject<Item> PETRIFIED_SPLASH7 = addPetrificationSplashPotion(7,PETRIFICATION_POTION7);
    public static final RegistryObject<Item> PETRIFIED_SPLASH8 = addPetrificationSplashPotion(8,PETRIFICATION_POTION8);
    public static final RegistryObject<Item> PETRIFIED_SPLASH9 = addPetrificationSplashPotion(9,PETRIFICATION_POTION9);

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }

    public static int POTION_INT = 0;

    public static RegistryObject<Item> addSimpleItem(int index, String name, int stackSize) {
        return ITEMLIST[index] = ITEMS.register(name, () -> new Item(new Item.Properties().group(PG).maxStackSize(stackSize)));
    }

    private static RegistryObject<Item> addPetrificationSplashPotion(int amplifier,Supplier<Potion> potion) {
        POTION_INT++;
        return POTIONLIST[POTION_INT - 1] = ITEMS.register("petrification_splash_potion_" + amplifier, () -> new PetrifiedSplashPotion(potion));
    }

}
