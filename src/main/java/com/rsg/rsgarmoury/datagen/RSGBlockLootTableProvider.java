package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.Set;

public class RSGBlockLootTableProvider extends BlockLootSubProvider {

    protected RSGBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags() , pRegistries);
    }

    @Override
    protected void generate() {

        dropSelf(RSGBlocks.SOUL_BLOCK.get());

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return RSGBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}