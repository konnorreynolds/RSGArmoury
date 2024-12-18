package com.rsg.rsgarmoury;

import com.mojang.logging.LogUtils;
import com.rsg.rsgarmoury.block.RSGBlocks;
import com.rsg.rsgarmoury.block.entity.RSGBlockEntities;
import com.rsg.rsgarmoury.effect.RSGEffects;
import com.rsg.rsgarmoury.item.RSGItems;
import com.rsg.rsgarmoury.screen.RSGMenuTypes;
import com.rsg.rsgarmoury.screen.SecondaryTradeStationScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// Very important Comment
// The value here should match an entry in the META-INF/mods.toml file
@Mod(RSGArmoury.MOD_ID)
public class RSGArmoury {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rsgarmoury";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public RSGArmoury() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        RSGItems.register(modEventBus);
        RSGBlocks.register(modEventBus);

        RSGBlockEntities.register(modEventBus);
        RSGMenuTypes.register(modEventBus);

        RSGEffects.register(modEventBus);

        RSGCreativeModeTabs.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            ItemBlockRenderTypes.setRenderLayer(RSGBlocks.SPAWNABLE_ARENA_WALL.get(), RenderType.translucent());

            EntityRenderers.register(EntityType.SNOWBALL, ThrownItemRenderer::new);

            MenuScreens.register(RSGMenuTypes.SECONDARY_TRADE_MENU.get(), SecondaryTradeStationScreen::new);

        }
    }
}