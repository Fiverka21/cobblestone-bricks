package org.example;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Main.MOD_ID)
public class Main {
    public static final String MOD_ID = "cobblestone_bricks";

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    public static final RegistryObject<Block> COBBLESTONE_BRICKS = registerBlock(
            "cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRICKS))
    );
    public static final RegistryObject<Block> COBBLESTONE_BRICK_STAIRS = registerBlock(
            "cobblestone_brick_stairs",
            () -> new StairBlock(COBBLESTONE_BRICKS.get()::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.BRICK_STAIRS))
    );
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_BRICKS = registerBlock(
            "mossy_cobblestone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICKS))
    );
    public static final RegistryObject<Block> MOSSY_COBBLESTONE_BRICK_STAIRS = registerBlock(
            "mossy_cobblestone_brick_stairs",
            () -> new StairBlock(MOSSY_COBBLESTONE_BRICKS.get()::defaultBlockState,
                    BlockBehaviour.Properties.copy(Blocks.MOSSY_STONE_BRICK_STAIRS))
    );

    public Main() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(
            String name, java.util.function.Supplier<T> block
    ) {
        RegistryObject<T> registeredBlock = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }

    @SubscribeEvent
    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(COBBLESTONE_BRICKS);
            event.accept(COBBLESTONE_BRICK_STAIRS);
            event.accept(MOSSY_COBBLESTONE_BRICKS);
            event.accept(MOSSY_COBBLESTONE_BRICK_STAIRS);
        }
    }
}
