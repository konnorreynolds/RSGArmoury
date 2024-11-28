package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.RSGBlocks;
import com.rsg.rsgarmoury.util.RSGTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RSGBlockTagProvider extends BlockTagsProvider {
    public RSGBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RSGArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(RSGBlocks.SOUL_BLOCK.get())
                .add(RSGBlocks.SPAWNABLE_ARENA_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(RSGBlocks.SOUL_BLOCK.get())
                .add(RSGBlocks.SPAWNABLE_ARENA_BLOCK.get());

        tag(RSGTags.Blocks.RSG_BLOCK)
                .add(RSGBlocks.SOUL_BLOCK.get())
                .add(RSGBlocks.SPAWNABLE_ARENA_BLOCK.get())
                .add(RSGBlocks.SPAWNABLE_ARENA_WALL.get());
    }
}
