package com.rsg.rsgarmoury.item.custom.weapons;

import com.rsg.rsgarmoury.item.RSGItems;
import com.rsg.rsgarmoury.util.RSGTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class RSGToolTiers {
    public static final Tier RSG_Sword = new ForgeTier(100, 4, -1f, 20,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(RSGItems.SOUL_IN_A_BOTTLE.get()),
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

}
