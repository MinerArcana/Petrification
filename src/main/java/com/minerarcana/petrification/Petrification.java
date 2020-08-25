package com.minerarcana.petrification;

import com.minerarcana.petrification.renderer.tile.PetrifiedTileXRenderer;
import com.minerarcana.petrification.renderer.tile.StatueRenderer;
import com.minerarcana.petrification.content.*;
import com.minerarcana.petrification.renderer.entity.CockatriceRenderer;
import com.minerarcana.petrification.util.PetrificationGroup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationBlocks.*;
import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;

@Mod(MOD_ID)
public class Petrification
{
    public static final String MOD_ID = "petrification";
    private static final Logger LOGGER = LogManager.getLogger();
    public static final PetrificationGroup PG =new PetrificationGroup();

    public Petrification() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        PetrificationBlocks.register(bus);
        PetrificationItems.register(bus);
        PetrificationEntities.register(bus);
        PetrificationEffects.register(bus);
        PetrificationPotions.register(bus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }
    private void setup(final FMLCommonSetupEvent event) {
        GlobalEntityTypeAttributes.put(COCKATRICE.get(), ChickenEntity.func_234187_eI_().create());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        //TileEntity Renderer
        ClientRegistry.bindTileEntityRenderer(ENTITY_STATUE.getTileEntityType(), StatueRenderer::new);
        ClientRegistry.bindTileEntityRenderer(PETRIFIED_TILE.getTileEntityType(), PetrifiedTileXRenderer::new);

        //Block Render
        RenderType cutout = RenderType.getCutout();
        RenderTypeLookup.setRenderLayer(STONE_NEST.getBlock(),cutout);

        //Entity Renderer
        RenderingRegistry.registerEntityRenderingHandler(COCKATRICE.get(), CockatriceRenderer::new);
    }
}
