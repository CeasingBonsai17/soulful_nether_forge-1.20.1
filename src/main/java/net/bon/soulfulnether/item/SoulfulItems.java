package net.bon.soulfulnether.item;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.CompatUtil;
import net.minecraft.world.item.*;
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
        public static final RegistryObject<Item> MARSHMARROW_HEART = ITEM.register("marshmarrow_heart", () ->
                new Item(new Item.Properties().food(SoulfulFoods.MARSHMARROW_FOODS)));

        public static final RegistryObject<Item> ASHEN_SNOW = ITEM.register("ashen_snow", () ->
                new Item(new Item.Properties()));


        public static final RegistryObject<Item> FROZEN_SOULROOT = ITEM.register("frozen_soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.SOULROOT)));
        public static final RegistryObject<Item> MARSHMARROW_MOCHI = ITEM.register("marshmarrow_mochi", () ->
                new Item(new Item.Properties().food(SoulfulFoods.MARSHMARROW_FOODS)));


        public static final RegistryObject<Item> FROSTBITTEN_SOULROOT = ITEM.register("frostbitten_soulroot", () ->
                new Item(new Item.Properties().food(SoulfulFoods.SOULROOT).food(SoulfulFoods.FROSTBITTEN_SOULROOT)));
        public static final RegistryObject<Item> FROSTBITTEN_POPSICLE = ITEM.register("frostbitten_popsicle", () ->
                new Item(new Item.Properties().food(SoulfulFoods.MARSHMARROW_FOODS).food(SoulfulFoods.FROSTBITTEN_POPSICLE).craftRemainder(Items.GLASS_BOTTLE)));



        public static void register(IEventBus eventBus) {
        ITEM.register(eventBus);
        }
}