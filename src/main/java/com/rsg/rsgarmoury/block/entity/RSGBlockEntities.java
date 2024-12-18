package com.rsg.rsgarmoury.block.entity;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.RSGBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RSGBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RSGArmoury.MOD_ID);

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

    public static final RegistryObject<BlockEntityType<SecondaryTradeStationEntity>> SECONDARY_TRADE_BE =
            BLOCK_ENTITIES.register("secondary_trade_be", () ->
                    BlockEntityType.Builder.of(SecondaryTradeStationEntity::new,
                            RSGBlocks.SECONDARY_TRADE_STATION.get()).build(null));


}
