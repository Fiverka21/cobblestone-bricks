package org.example;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "cobblestone_bricks";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredBlock<Block> COBBLESTONE_BRICKS = BLOCKS.register(
            "cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS))
    );
    public static final DeferredBlock<Block> COBBLESTONE_BRICK_STAIRS = BLOCKS.register(
            "cobblestone_brick_stairs",
            () -> new StairBlock(COBBLESTONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS))
    );
    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICKS = BLOCKS.register(
            "mossy_cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS))
    );
    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_BRICK_STAIRS = BLOCKS.register(
            "mossy_cobblestone_brick_stairs",
            () -> new StairBlock(MOSSY_COBBLESTONE_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICK_STAIRS))
    );

    public static final DeferredItem<BlockItem> COBBLESTONE_BRICKS_ITEM =
            ITEMS.registerSimpleBlockItem("cobblestone_bricks", COBBLESTONE_BRICKS);
    public static final DeferredItem<BlockItem> COBBLESTONE_BRICK_STAIRS_ITEM =
            ITEMS.registerSimpleBlockItem("cobblestone_brick_stairs", COBBLESTONE_BRICK_STAIRS);
    public static final DeferredItem<BlockItem> MOSSY_COBBLESTONE_BRICKS_ITEM =
            ITEMS.registerSimpleBlockItem("mossy_cobblestone_bricks", MOSSY_COBBLESTONE_BRICKS);
    public static final DeferredItem<BlockItem> MOSSY_COBBLESTONE_BRICK_STAIRS_ITEM =
            ITEMS.registerSimpleBlockItem("mossy_cobblestone_brick_stairs", MOSSY_COBBLESTONE_BRICK_STAIRS);

    public Main(IEventBus modEventBus, ModContainer modContainer) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(COBBLESTONE_BRICKS_ITEM);
            event.accept(COBBLESTONE_BRICK_STAIRS_ITEM);
            event.accept(MOSSY_COBBLESTONE_BRICKS_ITEM);
            event.accept(MOSSY_COBBLESTONE_BRICK_STAIRS_ITEM);
        }
    }
}
