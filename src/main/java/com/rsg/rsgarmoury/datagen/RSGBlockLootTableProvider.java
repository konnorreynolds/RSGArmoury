package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Set;

public class RSGBlockLootTableProvider extends BlockLootSubProvider {

    protected RSGBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        ArrayList<Block> iterableBlocks = new ArrayList<>(); // define empty list
        // Iterate through each block registered into ModBlocks.BLOCKS and add it into our list:
        RSGBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(iterableBlocks::add);
        // The above line requires that we need to have defined a loot table for every block in ModBlocks.BLOCKS.
        // Also add the vanilla blocks we are updating:
        return iterableBlocks;
    }
}