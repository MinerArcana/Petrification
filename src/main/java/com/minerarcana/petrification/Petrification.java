package com.minerarcana.petrification;

import com.minerarcana.petrification.item.CreativeTabPetrification;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static com.minerarcana.petrification.Petrification.MODID;
import static com.minerarcana.petrification.Petrification.NAME;
import static com.minerarcana.petrification.Petrification.VERSION;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class Petrification {
    public static final String MODID = "petrification";
    public static final String NAME = "Petrification";
    public static final String VERSION = "@VERSION@";

    @Instance
    public static Petrification instance;
    public static CreativeTabs petrificationTab = new CreativeTabPetrification();


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
