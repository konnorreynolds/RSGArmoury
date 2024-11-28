package com.rsg.rsgarmoury.item;

import com.rsg.rsgarmoury.RSGArmoury;
import com.rsg.rsgarmoury.item.custom.ConcealedHeart;
import com.rsg.rsgarmoury.item.custom.ConcentratedDefense;
import com.rsg.rsgarmoury.item.custom.RusticVial;
import com.rsg.rsgarmoury.item.custom.SacrificialDagger;
import com.rsg.rsgarmoury.item.custom.weapons.RSGProjectileItem;
import com.rsg.rsgarmoury.item.custom.weapons.RSGSword;
import com.rsg.rsgarmoury.item.custom.weapons.RSGToolTiers;
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
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SACRIFICIAL_DAGGER = ITEMS.register("sacrificial_dagger",
            () -> new SacrificialDagger(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> CONCEALED_HEART = ITEMS.register("concealed_heart",
            () -> new ConcealedHeart(new Item.Properties()));

    public static final RegistryObject<Item> RUSTIC_VIAL = ITEMS.register("rustic_vial",
            () -> new RusticVial(new Item.Properties()));

    public static final RegistryObject<Item> CONCENTRATED_DEFENSE = ITEMS.register("concentrated_defense",
            () -> new ConcentratedDefense(new Item.Properties()));

    public static final RegistryObject<Item> RSG_FOOD = ITEMS.register("rsg_food",
            () -> new Item(new Item.Properties()
                    .food(RSGConsumables.RSG_FOOD)
                    .stacksTo(16)));

    public static final RegistryObject<Item> RSG_SWORD = ITEMS.register("rsg_sword",
            () -> new RSGSword(RSGToolTiers.RSG_Sword, new Item.Properties()
                    .attributes(RSGSword.createAttributes(RSGToolTiers.RSG_Sword, 12, 2))));

    public static final RegistryObject<Item> RSG_PROJECTILE = ITEMS.register("rsg_projectile",
            () -> new RSGProjectileItem(new Item.Properties()
                    .durability(20)
                    .stacksTo(1)));


    // Calls the event handler i.e., eventBus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
