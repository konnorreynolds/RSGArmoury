package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.RSGItems;
import com.rsg.rsgarmoury.util.RSGTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RSGItemTagProvider extends ItemTagsProvider {
    public RSGItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, RSGArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(RSGTags.Items.RSG_ITEM)
                .add(RSGItems.CONCEALED_HEART.get())
                .add(RSGItems.CONCENTRATED_DEFENSE.get())
                .add(RSGItems.RUSTIC_VIAL.get())
                .add(RSGItems.SACRIFICIAL_DAGGER.get())

                .add(RSGItems.SOUL_IN_A_BOTTLE.get())
                .add(RSGItems.RSG_FOOD.get());

        tag(RSGTags.Items.RSG_WEAPONS)
                .add(RSGItems.SPELL_TAG.get())
                .add(RSGItems.THUNDER_HAMMER.get())
                .add(RSGItems.SPELL_STAFF.get());

        tag(RSGTags.Items.RSG_UPGRADES)
                .add(RSGItems.RED_SHARD.get())
                .add(RSGItems.BLUE_SHARD.get())
                .add(RSGItems.GREEN_SHARD.get())
                .add(RSGItems.WHITE_SCALE.get())
                .add(RSGItems.BLACK_SCALE.get());

    }
}
