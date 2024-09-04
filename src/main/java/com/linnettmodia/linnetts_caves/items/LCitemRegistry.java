package com.linnettmodia.linnetts_caves.items;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class LCitemRegistry {
    public static final DeferredRegister<Item>
            ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Linnetts_caves.MODID);

    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}
