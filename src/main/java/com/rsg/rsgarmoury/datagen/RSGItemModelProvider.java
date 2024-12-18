package com.rsg.rsgarmoury.datagen;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class RSGItemModelProvider extends ItemModelProvider {
    public RSGItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RSGArmoury.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(RSGItems.CONCEALED_HEART.get());
        basicItem(RSGItems.CONCENTRATED_DEFENSE.get());
        basicItem(RSGItems.RUSTIC_VIAL.get());
        basicItem(RSGItems.SACRIFICIAL_DAGGER.get());

        basicItem(RSGItems.RED_SHARD.get());
        basicItem(RSGItems.BLUE_SHARD.get());
        basicItem(RSGItems.GREEN_SHARD.get());
        basicItem(RSGItems.WHITE_SCALE.get());
        basicItem(RSGItems.BLACK_SCALE.get());

        basicItem(RSGItems.THUNDER_HAMMER.get());
        basicItem(RSGItems.SPELL_TAG.get());

        basicItem(RSGItems.SOUL_IN_A_BOTTLE.get());
        basicItem(RSGItems.RSG_FOOD.get());
    }

}
