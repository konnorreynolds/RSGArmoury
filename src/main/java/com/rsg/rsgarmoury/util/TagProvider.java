package com.rsg.rsgarmoury.util;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.CompletableFuture;

public class TagProvider extends EntityTypeTagsProvider {


    public TagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider) {
        super(pOutput, pProvider);
    }

    public void addInvertedHealing(Player player) {
        this.tag(EntityTypeTags.INVERTED_HEALING_AND_HARM).add(player.getType());
    }
}
