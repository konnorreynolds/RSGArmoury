package com.rsg.rsgarmoury.item;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.custom.ConcealedHeart;
import com.rsg.rsgarmoury.item.custom.ConcentratedDefense;
import com.rsg.rsgarmoury.item.custom.RusticVial;
import com.rsg.rsgarmoury.item.custom.SacrificialDagger;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RSGItems {
    // Adds items to the forge registries
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RSGArmoury.MOD_ID);

    // Makes a raw item called 'soul in a bottle'
    public static final RegistryObject<Item> SOUL_IN_A_BOTTLE = ITEMS.register("soul_in_a_bottle",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:soul_in_a_bottle")))));

    public static final RegistryObject<Item> SACRIFICIAL_DAGGER = ITEMS.register("sacrificial_dagger",
            () -> new SacrificialDagger(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:sacrificial_dagger")))
                    .stacksTo(1)));

    public static final RegistryObject<Item> CONCEALED_HEART = ITEMS.register("concealed_heart",
            () -> new ConcealedHeart(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:concealed_heart")))));

    public static final RegistryObject<Item> RUSTIC_VIAL = ITEMS.register("rustic_vial",
            () -> new RusticVial(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:rustic_vial")))));

    public static final RegistryObject<Item> CONCENTRATED_DEFENSE = ITEMS.register("concentrated_defense",
            () -> new ConcentratedDefense(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:concentrated_defense")))));

    public static final RegistryObject<Item> RSG_FOOD = ITEMS.register("rsg_food",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse("rsgarmoury:rsg_food")))
                    .food(RSGConsumables.TEST_FOOD_PROPERTIES, RSGConsumables.TEST_FOOD_CONSUMABLE)));


    // Calls the event handler i.e., eventBus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
