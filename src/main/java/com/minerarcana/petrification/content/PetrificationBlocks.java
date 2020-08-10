package com.minerarcana.petrification.content;

import com.minerarcana.petrification.blocks.StatueBottomBlock;
import com.minerarcana.petrification.tileentities.StatueTile;
import com.minerarcana.petrification.util.BlockRegistryObjectGroup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;

import static com.minerarcana.petrification.Petrification.MOD_ID;
import static com.minerarcana.petrification.Petrification.PG;

public class PetrificationBlocks {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final BlockRegistryObjectGroup<StatueBottomBlock, BlockItem, StatueTile> ENTITY_STATUE =
            new BlockRegistryObjectGroup<>("statue", StatueBottomBlock::new,blockItemCreator(), StatueTile::new).register(BLOCKS,ITEMS,TILE_ENTITIES);


    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        TILE_ENTITIES.register(modBus);
    }

    public static <B extends Block> Function<B, BlockItem> blockItemCreator() {
        return block -> new BlockItem(block, new Item.Properties().group(PG));
    }
}
