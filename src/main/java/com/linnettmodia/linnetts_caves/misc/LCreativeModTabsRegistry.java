package com.linnettmodia.linnetts_caves.misc;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import com.linnettmodia.linnetts_caves.blocks.LCblockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class LCreativeModTabsRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Linnetts_caves.MODID);
    public static final RegistryObject<CreativeModeTab> DESSERT_TAB = CREATIVE_MODE_TABS.register("dessert_tab",()-> CreativeModeTab.builder().icon(()-> new ItemStack(LCblockRegistry.BISCUIT_ST_CREAM_BLOCK.get().asItem())).title(Component.translatable("creativetab.dessert_tab")).displayItems((itemDisplayParameters, output) -> {
        output.accept(LCblockRegistry.CHERRY_BOMB.get());
        output.accept(LCblockRegistry.FIZZY_STICK.get());
        output.accept(LCblockRegistry.RED_FIZZY_BLOCK.get());
        output.accept(LCblockRegistry.YELLOW_FIZZY_BLOCK.get());
        output.accept(LCblockRegistry.GREEN_FIZZY_BLOCK.get());
        output.accept(LCblockRegistry.BLUE_FIZZY_BLOCK.get());
        output.accept(LCblockRegistry.PEPPERMINT_CANDLE.get());
        output.accept(LCblockRegistry.CHOCOLATE_GROWTHS.get());
        output.accept(LCblockRegistry.JELLY_BLOCK.get());
        output.accept(LCblockRegistry.RED_JELLY_BLOCK.get());
        output.accept(LCblockRegistry.BLUE_JELLY_BLOCK.get());
        output.accept(LCblockRegistry.BISCUIT_ST_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.BISCUIT_VA_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.BISCUIT_CH_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.STRAWBERRY_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.VANILLA_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.CHOCOLATE_CREAM_BLOCK.get());
        output.accept(LCblockRegistry.CHOCOLATE_BISCUIT_BLOCK.get());
        output.accept(LCblockRegistry.WAFFLE_BLOCK.get());
        output.accept(LCblockRegistry.WAFFLE_BRICK.get());
        output.accept(LCblockRegistry.WAFFLE_SLAB.get());
        output.accept(LCblockRegistry.WAFFLE_STAIRS.get());
        output.accept(LCblockRegistry.WAFFLE_WALL.get());
        output.accept(LCblockRegistry.WAFFLE_BRICK_SLAB.get());
        output.accept(LCblockRegistry.WAFFLE_BRICK_STAIRS.get());
        output.accept(LCblockRegistry.WAFFLE_BRICK_WALL.get());
        output.accept(LCblockRegistry.CHOCOLATE_DOUGH_BLOCK.get());
        output.accept(LCblockRegistry.CHOCOLATE_DOUGH_SLAB.get());
        output.accept(LCblockRegistry.CHOCOLATE_DOUGH_STAIRS.get());
        output.accept(LCblockRegistry.CHOCOLATE_DOUGH_WALL.get());
    }).build());
    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
