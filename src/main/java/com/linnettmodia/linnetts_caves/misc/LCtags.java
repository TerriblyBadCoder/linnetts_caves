package com.linnettmodia.linnetts_caves.misc;

import com.linnettmodia.linnetts_caves.Linnetts_caves;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class LCtags {
    public static class Blocks {

        public static final TagKey<Block> CHOCOLATE_GROWTHS_MAY = tag("chocolate_growths_may");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Linnetts_caves.MODID, name));
        }
    }
    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Linnetts_caves.MODID, name));
        }
    }
}
