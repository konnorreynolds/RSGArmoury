package com.rsg.rsgarmoury.util;

import com.rsg.rsgarmoury.RSGArmoury;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RSGTags {
    public static class Blocks {

        public static final TagKey<Block> RSG_BLOCK = createTag("rsg_block");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RSGArmoury.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> RSG_ITEM = createTag("rsg_item");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RSGArmoury.MOD_ID, name));
        }
    }
}
