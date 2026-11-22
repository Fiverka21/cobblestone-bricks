package org.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
            new Block(blockProperties("cobblestone_bricks", Blocks.BRICKS))
    );
    public static final Block COBBLESTONE_BRICK_STAIRS = registerBlock(
            "cobblestone_brick_stairs",
            new StairBlock(COBBLESTONE_BRICKS.defaultBlockState(),
                    blockProperties("cobblestone_brick_stairs", Blocks.BRICK_STAIRS))
    );
    public static final Block MOSSY_COBBLESTONE_BRICKS = registerBlock(
            "mossy_cobblestone_bricks",
            new Block(blockProperties("mossy_cobblestone_bricks", Blocks.MOSSY_STONE_BRICKS))
    );
    public static final Block MOSSY_COBBLESTONE_BRICK_STAIRS = registerBlock(
            "mossy_cobblestone_brick_stairs",
            new StairBlock(MOSSY_COBBLESTONE_BRICKS.defaultBlockState(),
                    blockProperties("mossy_cobblestone_brick_stairs", Blocks.MOSSY_STONE_BRICK_STAIRS))
    );

    private static BlockBehaviour.Properties blockProperties(String name, Block template) {
        return BlockBehaviour.Properties.ofFullCopy(template)
                .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MOD_ID, name)));
    }

    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block,
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
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
