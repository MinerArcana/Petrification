package com.minerarcana.petrification;

import com.minerarcana.petrification.block.BlockStoneNest;
import com.minerarcana.petrification.entity.EntityCockatrice;
import com.minerarcana.petrification.item.ItemBlockBase;
import com.minerarcana.petrification.potion.PotionBase;
import com.minerarcana.petrification.potion.PotionTypeBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Objects;

import static com.minerarcana.petrification.Petrification.MODID;

@EventBusSubscriber(modid = MODID)
public class EventHandler {
    private static ItemBlock itemStoneNest;
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> blockRegistryEvent) {
        BlockStoneNest blockStoneNest = new BlockStoneNest();
        blockStoneNest.setCreativeTab(Petrification.petrificationTab);
        blockRegistryEvent.getRegistry().register(blockStoneNest);
        itemStoneNest = new ItemBlockBase(blockStoneNest);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().registerAll(itemStoneNest);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent modelRegistryEvent) {
        Objects.requireNonNull(itemStoneNest.getRegistryName(), "What did you do");
        ModelLoader.setCustomModelResourceLocation(itemStoneNest, 0,
                new ModelResourceLocation(itemStoneNest.getRegistryName(), "egg=true"));
        ModelLoader.setCustomModelResourceLocation(itemStoneNest, 1,
                new ModelResourceLocation(itemStoneNest.getRegistryName(), "egg=false"));
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> entityRegistryEvent) {
        int id = 0;
        ResourceLocation cockatriceRL = new ResourceLocation(MODID, EntityCockatrice.NAME);
        EntityRegistry.registerModEntity(cockatriceRL, EntityCockatrice.class, EntityCockatrice.NAME, id,
                Petrification.instance, 64, 8, true);
        EntityRegistry.registerEgg(cockatriceRL, Color.GRAY.getRGB(), Color.DARK_GRAY.getRGB());
    }

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> potionRegistryEvent) {
        potionRegistryEvent.getRegistry().register(PotionBase.PETRIFICATION);
        potionRegistryEvent.getRegistry().register(PotionBase.REVIVIFY);
    }

}
