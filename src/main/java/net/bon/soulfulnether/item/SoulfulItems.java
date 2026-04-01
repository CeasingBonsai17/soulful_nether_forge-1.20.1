package net.bon.soulfulnether.item;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.item.type.AshenSnowballItem;
import net.bon.soulfulnether.item.type.BoneWyrmLarvaItem;
import net.bon.soulfulnether.item.type.FrostbittenPopsicleItem;
import net.bon.soulfulnether.item.type.MarshmarrowStickItem;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class  SoulfulItems {
        public static final DeferredRegister<Item> ITEM =
                DeferredRegister.create(ForgeRegistries.ITEMS, SoulfulNether.MOD_ID);
        public static final RegistryObject<Item> SOULROOT_SEEDS = ITEM.register("soulroot_seeds", () ->
                new ItemNameBlockItem(SoulfulBlocks.SOULROOTS.get(), new Item.Properties()));
        public static final RegistryObject<Item> SOULROOT = ITEM.register("soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.SOULROOT)));
        public static final RegistryObject<Item> CHARRED_SOULROOT = ITEM.register("charred_soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.CHARRED_SOULROOT)));
        public static final RegistryObject<Item> SOULROOT_PIE = ITEM.register("soulroot_pie", () ->
                new Item(new Item.Properties().food(SoulfulFoods.SOULROOT_FOODS)));
        public static final RegistryObject<Item> FROZEN_SOULROOT = ITEM.register("frozen_soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.SOULROOT)));
        public static final RegistryObject<Item> MARSHMARROW_MOCHI = ITEM.register("marshmarrow_mochi", () ->
                new Item(new Item.Properties().food(SoulfulFoods.MARSHMARROW_FOODS)));

        public static final RegistryObject<Item> FRIGHT_SIGN = ITEM.register("fright_sign", () ->
                new SignItem(new Item.Properties().stacksTo(16), SoulfulBlocks.FRIGHT_SIGN.get(), SoulfulBlocks.FRIGHT_WALL_SIGN.get()));
        public static final RegistryObject<Item> FRIGHT_HANGING_SIGN = ITEM.register("fright_hanging_sign", () ->
                new HangingSignItem(SoulfulBlocks.FRIGHT_HANGING_SIGN.get(), SoulfulBlocks.FRIGHT_WALL_HANGING_SIGN.get(), new Item.Properties()));
        public static final RegistryObject<Item> MARSHMARROW = ITEM.register("marshmarrow", () ->
                new BlockItem(SoulfulBlocks.MARSHMARROW.get(), new Item.Properties().food(SoulfulFoods.MARSHMARROW)));
        public static final RegistryObject<Item> TOASTY_MARSHMARROW = ITEM.register("toasty_marshmarrow", () ->
                new BlockItem(SoulfulBlocks.TOASTY_MARSHMARROW.get(), new Item.Properties().food(SoulfulFoods.TOASTY_MARSHMARROW)));
        public static final RegistryObject<Item> ROASTED_MARSHMARROW = ITEM.register("roasted_marshmarrow", () ->
                new BlockItem(SoulfulBlocks.ROASTED_MARSHMARROW.get(), new Item.Properties().food(SoulfulFoods.ROASTED_MARSHMARROW)));
        public static final RegistryObject<Item> MARSHMARROW_ON_A_STICK = ITEM.register("marshmarrow_on_a_stick", () ->
                new MarshmarrowStickItem((new Item.Properties()).durability(25)));
        public static final RegistryObject<Item> MARSHMARROW_HEART = ITEM.register("marshmarrow_heart", () ->
                new Item(new Item.Properties().food(SoulfulFoods.MARSHMARROW_FOODS)));
        public static final RegistryObject<Item> BONE_WYRM_SPAWN_EGG = ITEM.register("bone_wyrm_spawn_egg", () ->
                new ForgeSpawnEggItem(SoulfulEntityTypes.BONE_WYRM, 0xebeade, 0xc5c09d, new Item.Properties()));
        public static final RegistryObject<Item> BONE_WYRM_LARVA = ITEM.register("bone_wyrm_larva", () ->
                new BoneWyrmLarvaItem(new Item.Properties().food(SoulfulFoods.BONE_WYRM_LARVA)));

        public static final RegistryObject<Item> ASHEN_SNOW = ITEM.register("ashen_snow", () ->
                new Item(new Item.Properties()));
        public static final RegistryObject<Item> ASHEN_SNOWBALL = ITEM.register("ashen_snowball", () ->
                new AshenSnowballItem(new Item.Properties()));

        public static final RegistryObject<Item> GLOOMY_AMBER_BRICK = ITEM.register("gloomy_amber_brick", () ->
                new Item(new Item.Properties()));




        public static final RegistryObject<Item> FROSTBITTEN_SOULROOT = ITEM.register("frostbitten_soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.FROSTBITTEN_SOULROOT)));
        public static final RegistryObject<Item> FROSTBITTEN_POPSICLE = ITEM.register("frostbitten_popsicle", () ->
                new FrostbittenPopsicleItem(new Item.Properties().food(SoulfulFoods.FROSTBITTEN_POPSICLE).craftRemainder(Items.STICK)));






        public static void init(IEventBus eventBus) {
                ITEM.register(eventBus);
    }
}