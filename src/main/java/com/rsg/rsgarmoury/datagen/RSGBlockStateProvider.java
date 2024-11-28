package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RSGBlockStateProvider extends BlockStateProvider {
    public RSGBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RSGArmoury.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(RSGBlocks.SOUL_BLOCK);
        // blockWithItem(RSGBlocks.SPAWNABLE_ARENA_BLOCK); no texture
        blockWithItem(RSGBlocks.SPAWNABLE_ARENA_WALL);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
