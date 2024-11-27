package com.rsg.rsgarmoury.block;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.block.custom.SpawnableArenaBlock;
import com.rsg.rsgarmoury.item.RSGItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TransparentBlock;
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


    public static final RegistryObject<Block> SOUL_BLOCK = registerBlock("soul_block",
            () -> new Block(BlockBehaviour.Properties.of().
                    setId(ResourceKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("rsgarmoury:soul_block")))
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.GLASS)
                    .speedFactor(1)
                    .jumpFactor(4)));

    public static final RegistryObject<Block> SPAWNABLE_ARENA_BLOCK = registerBlock("spawnable_arena_block",
            () -> new SpawnableArenaBlock(BlockBehaviour.Properties.of().
                    setId(ResourceKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("rsgarmoury:spawnable_arena_block")))
                    .strength(6f).requiresCorrectToolForDrops()));


    public static final RegistryObject<TransparentBlock> SPAWNABLE_ARENA_WALL = registerBlock("spawnable_arena_wall",
            () -> new TransparentBlock(BlockBehaviour.Properties.of().noOcclusion()
                    .setId(ResourceKey.create(ForgeRegistries.BLOCKS.getRegistryKey(), ResourceLocation.parse("rsgarmoury:spawnable_arena_wall")))));


    // Command to register the block by first making it an item using registerBlockItem
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Registers the block as an item
    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        RSGItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().
                setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:" + name)))) {
            @Override
            public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                pTooltipComponents.add(Component.translatable("tooltip.rsgarmoury." + name));
                super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            }
        });
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
