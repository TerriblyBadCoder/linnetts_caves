package com.linnettmodia.linnetts_caves.blocks;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.blocks.custom.*;
import com.linnettmodia.linnetts_caves.items.LCitemRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class LCblockRegistry {
    public static final DeferredRegister<Block>
            BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Linnetts_caves.MODID);
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    public static final RegistryObject<Block> PEPPERMINT_CANDLE = registerBlock("pink_party_candle",
            () -> new PeppermintCandleBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(PeppermintCandleBlock.LIGHT_EMISSION)));
    public static final RegistryObject<Block> CHERRY_BOMB = registerBlock("exploding_cherry",
            () -> new CherryBombBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE).mapColor(MapColor.COLOR_PINK)));
    public static final RegistryObject<Block> BISCUIT_CH_CREAM_BLOCK = registerBlock("biscuit_with_chocolate_cream_block",
            () -> new CreamedBiscuitBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_BROWN)));
    public static final RegistryObject<Block> BISCUIT_ST_CREAM_BLOCK = registerBlock("biscuit_with_strawberry_cream_block",
            () -> new CreamedBiscuitBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.COLOR_PINK)));
    public static final RegistryObject<Block> BISCUIT_VA_CREAM_BLOCK = registerBlock("biscuit_with_vanilla_cream_block",
            () -> new CreamedBiscuitBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final RegistryObject<Block> CHOCOLATE_BISCUIT_BLOCK = registerBlock("chocolate_biscuit_block",
            () -> new CreamedBiscuitBlock(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> JELLY_BLOCK = registerBlock("jelly_block",
            () -> new JellyBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)));
    public static final RegistryObject<Block> BLUE_JELLY_BLOCK = registerBlock("blue_jelly_block",
            () -> new JellyBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_CYAN)));

    public static final RegistryObject<Block> RED_JELLY_BLOCK = registerBlock("red_jelly_block",
            () -> new JellyBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_RED)));

    public static final RegistryObject<Block> WAFFLE_BLOCK = registerBlock("waffle_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_BRICK = registerBlock("waffle_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_BRICK_SLAB = registerBlock("waffle_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_BRICK_STAIRS = registerBlock("waffle_brick_stairs",
            () -> new StairBlock(WAFFLE_BRICK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_BRICK_WALL = registerBlock("waffle_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_SLAB = registerBlock("waffle_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_STAIRS = registerBlock("waffle_stairs",
            () -> new StairBlock(WAFFLE_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> WAFFLE_WALL = registerBlock("waffle_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.SAND)));
    public static final RegistryObject<Block> CHOCOLATE_CREAM_BLOCK = registerBlock("chocolate_cream_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_BROWN)));
    public static final RegistryObject<Block> STRAWBERRY_CREAM_BLOCK = registerBlock("strawberry_cream_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> VANILLA_CREAM_BLOCK = registerBlock("vanilla_cream_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.TERRACOTTA_WHITE)));
    public static final RegistryObject<Block> CHOCOLATE_DOUGH_BLOCK = registerBlock("chocolate_dough_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> CHOCOLATE_DOUGH_SLAB = registerBlock("chocolate_dough_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> CHOCOLATE_DOUGH_STAIRS = registerBlock("chocolate_dough_stairs",
            () -> new StairBlock(CHOCOLATE_DOUGH_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> CHOCOLATE_DOUGH_WALL = registerBlock("chocolate_dough_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> FIZZY_STICK = registerBlock("fizzy_stick",
            () -> new FizzyStickBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).destroyTime(2)));
    public static final RegistryObject<Block> RED_FIZZY_BLOCK = registerBlock("red_fizzy_stone_block",
            () -> new FizzyStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).destroyTime(2)));
    public static final RegistryObject<Block> YELLOW_FIZZY_BLOCK = registerBlock("yellow_fizzy_stone_block",
            () -> new FizzyStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).destroyTime(2)));
    public static final RegistryObject<Block> GREEN_FIZZY_BLOCK = registerBlock("green_fizzy_stone_block",
            () -> new FizzyStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).destroyTime(2)));
    public static final RegistryObject<Block> BLUE_FIZZY_BLOCK = registerBlock("blue_fizzy_stone_block",
            () -> new FizzyStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).destroyTime(2)));

    public static final RegistryObject<Block> CHOCOLATE_GROWTHS = registerBlock("chocolate_growths",
            () -> new ChocolateGrowthsBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).mapColor(MapColor.COLOR_BROWN).dynamicShape()));
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return LCitemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
