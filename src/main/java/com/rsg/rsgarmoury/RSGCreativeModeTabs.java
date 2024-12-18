package com.rsg.rsgarmoury;

import com.rsg.rsgarmoury.block.RSGBlocks;
import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RSGCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RSGArmoury.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RSG_ARMOURY_TAB = CREATIVE_MODE_TABS.register("rsg_armoury_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(RSGItems.SOUL_IN_A_BOTTLE.get()))
                    //.withTabsBefore(RSG_Armoury_Tab) // use with multiple tabs to designate tab order
                    .title(Component.translatable("creativetab.rsgarmoury.rsg_armoury_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(RSGItems.SACRIFICIAL_DAGGER.get());
                        output.accept(RSGItems.CONCEALED_HEART.get());
                        output.accept(RSGItems.RUSTIC_VIAL.get());
                        output.accept(RSGItems.CONCENTRATED_DEFENSE.get());
                        output.accept(RSGItems.SOUL_IN_A_BOTTLE.get());
                        output.accept(RSGItems.RSG_FOOD.get());

                        output.accept(RSGItems.RED_SHARD.get());
                        output.accept(RSGItems.GREEN_SHARD.get());
                        output.accept(RSGItems.BLUE_SHARD.get());
                        output.accept(RSGItems.WHITE_SCALE.get());
                        output.accept(RSGItems.BLACK_SCALE.get());

                        output.accept(RSGItems.THUNDER_HAMMER.get());
                        output.accept(RSGItems.SPELL_TAG.get());
                        output.accept(RSGItems.SPELL_STAFF.get());

                        output.accept(RSGBlocks.SPAWNABLE_ARENA_BLOCK.get());
                        output.accept(RSGBlocks.SPAWNABLE_ARENA_WALL.get());
                        output.accept(RSGBlocks.SECONDARY_TRADE_STATION.get());


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
