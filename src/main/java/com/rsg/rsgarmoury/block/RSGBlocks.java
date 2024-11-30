package com.rsg.rsgarmoury.block;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.custom.SpawnableArenaBlock;
import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class RSGBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RSGArmoury.MOD_ID);


    public static final RegistryObject<Block> SPAWNABLE_ARENA_BLOCK = registerBlock("spawnable_arena_block", false,
            () -> new SpawnableArenaBlock(BlockBehaviour.Properties.of()
                    .strength(50)
                    .noLootTable()));


    public static final RegistryObject<Block> SPAWNABLE_ARENA_WALL = registerBlock("spawnable_arena_wall", true,
            () -> new StainedGlassBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS)
                    .strength(-1)
                    .noOcclusion()
                    .noLootTable()));


    // Command to register the block by first making it an item using registerBlockItem
    private static <T extends Block> RegistryObject<T> registerBlock(String name, boolean hoverText, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, hoverText, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, boolean hoverText, RegistryObject<T> block) {
        RSGItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()) {
            @Override
            public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {

                if (hoverText) {
                    pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury." + name));
                }

                super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            }
        });
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
