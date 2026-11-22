package org.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class Main implements ModInitializer {
    public static final String MOD_ID = "cobblestone_bricks";

    public static final Block COBBLESTONE_BRICKS = registerBlock(
            "cobblestone_bricks",
            new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );
    public static final Block COBBLESTONE_BRICK_STAIRS = registerBlock(
            "cobblestone_brick_stairs",
            new StairBlock(COBBLESTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.BRICK_STAIRS))
    );
    public static final Block MOSSY_COBBLESTONE_BRICKS = registerBlock(
            "mossy_cobblestone_bricks",
            new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS))
    );
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = registerBlock(
            "mossy_cobblestone_brick_stairs",
            new StairBlock(MOSSY_COBBLESTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICK_STAIRS))
    );

    private static Block registerBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation(MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        return block;
    }

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.accept(COBBLESTONE_BRICKS);
            entries.accept(COBBLESTONE_BRICK_STAIRS);
            entries.accept(MOSSY_COBBLESTONE_BRICKS);
            entries.accept(MOSSY_COBBLESTONE_BRICK_STAIRS);
        });
    }
}
