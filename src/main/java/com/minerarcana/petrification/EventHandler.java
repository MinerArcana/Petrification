package com.minerarcana.petrification;

import com.google.common.collect.Lists;
import com.minerarcana.petrification.block.BlockStoneNest;
import com.minerarcana.petrification.item.CreativeTabPetrification;
import com.minerarcana.petrification.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
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
}
