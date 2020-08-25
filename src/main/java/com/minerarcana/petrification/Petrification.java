package com.minerarcana.petrification;

import com.minerarcana.petrification.renderer.tile.PetrifiedTileXRenderer;
import com.minerarcana.petrification.renderer.tile.StatueRenderer;
import com.minerarcana.petrification.content.*;
import com.minerarcana.petrification.renderer.entity.CockatriceRenderer;
import com.minerarcana.petrification.util.PetrificationGroup;
import com.minerarcana.petrification.util.PotionIngredient;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.CallbackI;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.content.PetrificationBlocks.*;
import static com.minerarcana.petrification.content.PetrificationEntities.COCKATRICE;
import static com.minerarcana.petrification.content.PetrificationItems.*;
import static com.minerarcana.petrification.content.PetrificationPotions.*;

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

    private void serverStartUp(final FMLServerStartingEvent event){
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(Potions.AWKWARD), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), potionToItemStack(PETRIFICATION_POTION.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION.get()), Ingredient.fromItems(Items.REDSTONE), potionToItemStack(PETRIFICATION_POTION1.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION1.get()), Ingredient.fromItems(Items.REDSTONE), potionToItemStack(PETRIFICATION_POTION2.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION2.get()), Ingredient.fromItems(Items.REDSTONE), potionToItemStack(PETRIFICATION_POTION3.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION3.get()), Ingredient.fromItems(Items.REDSTONE), potionToItemStack(PETRIFICATION_POTION4.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION4.get()), Ingredient.fromItems(Items.GLOWSTONE), potionToItemStack(PETRIFICATION_POTION5.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION5.get()), Ingredient.fromItems(Items.GLOWSTONE), potionToItemStack(PETRIFICATION_POTION6.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION6.get()), Ingredient.fromItems(Items.GLOWSTONE), potionToItemStack(PETRIFICATION_POTION7.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION7.get()), Ingredient.fromItems(Items.GLOWSTONE), potionToItemStack(PETRIFICATION_POTION8.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION8.get()), Ingredient.fromItems(Items.GLOWSTONE), potionToItemStack(PETRIFICATION_POTION9.get()));



        //Splash Recipes
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION2.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION3.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH1.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION4.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH2.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION5.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH3.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION6.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH4.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION7.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH5.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION8.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH6.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(PETRIFICATION_POTION9.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH7.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.fromItems(PETRIFIED_SPLASH7.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH8.get()));
        BrewingRecipeRegistry.addRecipe(Ingredient.fromItems(PETRIFIED_SPLASH8.get()), Ingredient.fromItems(PetrificationItems.STONE_EGG.get()), new ItemStack(PETRIFIED_SPLASH9.get()));

        BrewingRecipeRegistry.addRecipe(Ingredient.fromItems(PETRIFIED_SPLASH.get()), Ingredient.fromItems(Items.EGG), potionToItemStack(REVIVIFY_POTION.get()));
        BrewingRecipeRegistry.addRecipe(PotionIngredient.asPotion(REVIVIFY_POTION.get()), Ingredient.fromItems(Items.EGG), new ItemStack(REVIVIFY_SPLASH.get()));

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

    private static ItemStack potionToItemStack(Potion potion) {
        return PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), potion);
    }
}
