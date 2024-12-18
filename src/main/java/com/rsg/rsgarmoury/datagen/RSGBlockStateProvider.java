package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class RSGBlockStateProvider extends BlockStateProvider {
    ExistingFileHelper existingFileHelper;

    public RSGBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RSGArmoury.MOD_ID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(RSGBlocks.SPAWNABLE_ARENA_WALL);
        blockWithItem(RSGBlocks.SPAWNABLE_ARENA_BLOCK);

        simpleBlockWithItem(RSGBlocks.SECONDARY_TRADE_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/secondary_trade_station")));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }


}
