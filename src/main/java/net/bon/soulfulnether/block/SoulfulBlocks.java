package net.bon.soulfulnether.block;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.block.grower.HugeGloomFungusGrower;
import net.bon.soulfulnether.block.type.*;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.util.CompatUtil;
import net.bon.soulfulnether.worldgen.feature.SoulfulConfiguredFeatures;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

 @SuppressWarnings("deprecation unused")

public class SoulfulBlocks {
    public static final DeferredRegister<Block> BLOCK =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SoulfulNether.MOD_ID);

    public static final RegistryObject<Block> SOULROOTS = registerBlockWithoutItem("soulroots", () ->
            new SoulrootsBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS).mapColor(MapColor.COLOR_CYAN).randomTicks()));

    public static final RegistryObject<Block> ROASTED_MARSHMARROW = registerBlockWithoutItem("roasted_marshmarrow", () ->
            new MarshmarrowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).strength(0.2F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> ROASTED_MARSHMARROW_STACK = registerBlock("roasted_marshmarrow_stack", () ->
            new MarshmarrowStackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.XYLOPHONE).strength(0.5F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> TOASTY_MARSHMARROW = registerBlockWithoutItem("toasty_marshmarrow", () ->
            new ToastingMarshmarrowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).strength(0.2F).sound(SoundType.WOOL), 0.5F, ROASTED_MARSHMARROW.get()));
    public static final RegistryObject<Block> TOASTY_MARSHMARROW_STACK = registerBlock("toasty_marshmarrow_stack", () ->
            new ToastingMarshmarrowStackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.RAW_IRON).instrument(NoteBlockInstrument.XYLOPHONE).strength(0.5F).sound(SoundType.WOOL).randomTicks(), 0.5F, ROASTED_MARSHMARROW_STACK.get()));
    public static final RegistryObject<Block> MARSHMARROW = registerBlockWithoutItem("marshmarrow", () ->
            new ToastingMarshmarrowBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.2F).sound(SoundType.WOOL), 0.1F, TOASTY_MARSHMARROW.get()));
    public static final RegistryObject<Block> MARSHMARROW_STACK = registerBlock("marshmarrow_stack", () ->
            new ToastingMarshmarrowStackBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).instrument(NoteBlockInstrument.XYLOPHONE).strength(0.5F).sound(SoundType.WOOL).randomTicks(), 0.1F, TOASTY_MARSHMARROW_STACK.get()));

    public static final RegistryObject<Block> SPIRALING_VINES = registerBlock("spiraling_vines", () ->
            new SpiralingVinesBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES).mapColor(MapColor.COLOR_BLACK).randomTicks()));
    public static final RegistryObject<Block> SPIRALING_VINES_PLANT = registerBlockWithoutItem("spiraling_vines_plant", () ->
            new SpiralingVinesPlantBlock(BlockBehaviour.Properties.copy(Blocks.TWISTING_VINES_PLANT).mapColor(MapColor.COLOR_BLACK)));

    public static final RegistryObject<Block> STRIPPED_FRIGHT_STEM = registerBlock("stripped_fright_stem", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_STEM = registerBlock("fright_stem", () ->
            new StrippablePillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM).mapColor((blockState) -> {return blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.CLAY : MapColor.COLOR_BLUE;}), STRIPPED_FRIGHT_STEM));
    public static final RegistryObject<Block> STRIPPED_FRIGHT_HYPHAE = registerBlock("stripped_fright_hyphae", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_HYPHAE = registerBlock("fright_hyphae", () ->
            new StrippablePillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE).mapColor(MapColor.COLOR_BLUE), STRIPPED_FRIGHT_HYPHAE));
    public static final RegistryObject<Block> FRIGHT_PLANKS = registerBlock("fright_planks", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_SLAB = registerBlock("fright_slab", () ->
            new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SLAB).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_STAIRS = registerBlock("fright_stairs", () ->
            new StairBlock(FRIGHT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.WARPED_STAIRS).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_FENCE = registerBlock("fright_fence", () ->
            new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> FRIGHT_FENCE_GATE = registerBlock("fright_fence_gate", () ->
            new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE_GATE).mapColor(MapColor.CLAY), SoulfulWoodType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_DOOR = registerBlock("fright_door", () ->
            new DoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_DOOR).mapColor(MapColor.CLAY), SoulfulBlockSetType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_TRAPDOOR = registerBlock("fright_trapdoor", () ->
            new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_TRAPDOOR).mapColor(MapColor.CLAY), SoulfulBlockSetType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_PRESSURE_PLATE = registerBlock("fright_pressure_plate", () ->
            new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).mapColor(MapColor.CLAY), SoulfulBlockSetType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_BUTTON = registerBlock("fright_button", () ->
            new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_BUTTON),SoulfulBlockSetType.FRIGHT,25,true));
    public static final RegistryObject<Block> FRIGHT_SIGN = registerBlockWithoutItem("fright_sign", () ->
            new StandingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SIGN).mapColor(MapColor.CLAY), SoulfulWoodType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_WALL_SIGN = registerBlockWithoutItem("fright_wall_sign", () ->
            new WallSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WALL_SIGN).mapColor(MapColor.CLAY), SoulfulWoodType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_HANGING_SIGN = registerBlockWithoutItem("fright_hanging_sign", () ->
            new CeilingHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HANGING_SIGN).mapColor(MapColor.CLAY), SoulfulWoodType.FRIGHT));
    public static final RegistryObject<Block> FRIGHT_WALL_HANGING_SIGN = registerBlockWithoutItem("fright_wall_hanging_sign", () ->
            new WallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WALL_HANGING_SIGN).mapColor(MapColor.CLAY), SoulfulWoodType.FRIGHT));
    public static final RegistryObject<Block> LICHOSS_BLOCK = registerBlock("lichoss_block", () ->
            new LichossBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<Block> HANGING_LICHOSS = registerBlock("hanging_lichoss", () ->
            new HangingLichossBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY).randomTicks()));
    public static final RegistryObject<Block> HANGING_LICHOSS_PLANT = registerBlockWithoutItem("hanging_lichoss_plant", () ->
            new HangingLichossPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> LICHOSS_CARPET = registerBlock("lichoss_carpet", () ->
            new LichossCarpetBlock(BlockBehaviour.Properties.copy(Blocks.MOSS_CARPET).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<Block> EMBER_ROOTS = registerBlock("ember_roots", () ->
            new EmberRootsBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_ROOTS).mapColor(MapColor.COLOR_CYAN)));
    public static final RegistryObject<Block> FRIGHT_WART_BLOCK = registerBlock("fright_wart_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK).mapColor(MapColor.COLOR_BLACK)));
    public static final RegistryObject<Block> FRIGHT_FUNGUS = registerBlock("fright_fungus", () ->
            new FrightFungusBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FUNGUS).mapColor(MapColor.COLOR_BLACK), SoulfulConfiguredFeatures.FRIGHT_FUNGUS_PLANTED, LICHOSS_BLOCK.get()));
    public static final RegistryObject<Block> FRIGHT_ROOTS = registerBlock("fright_roots", () ->
            new FrightRootsBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_ROOTS).mapColor(MapColor.COLOR_BLACK)));



      
    public static final RegistryObject<Block> ASHEN_SNOW_BLOCK = registerBlock("ashen_snow_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.SNOW_BLOCK).mapColor(MapColor.CLAY).sound(SoundType.SAND)));
    public static final RegistryObject<Block> ASHEN_SNOW_LAYER = registerBlock("ashen_snow_layer", () ->
            new AshenSnowLayerBlock(BlockBehaviour.Properties.copy(Blocks.SNOW).mapColor(MapColor.CLAY).sound(SoundType.SAND)));
    public static final RegistryObject<Block> ASHEN_BASALT = registerBlock("ashen_basalt", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> SMOOTH_ASHEN_BASALT = registerBlock("smooth_ashen_basalt", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> POLISHED_ASHEN_BASALT = registerBlock("polished_ashen_basalt", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> VOLCANIC_QUARTZ_BLOCK = registerBlock("volcanic_quartz_block", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> VOLCANIC_QUARTZ_BRICKS = registerBlock("volcanic_quartz_bricks", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> POLISHED_VOLCANIC_QUARTZ = registerBlock("polished_volcanic_quartz", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.POLISHED_BASALT).mapColor(MapColor.COLOR_LIGHT_GRAY)));
    public static final RegistryObject<Block> VOLCANIC_ICE = registerBlock("volcanic_ice", () ->
            new HalfTransparentBlock(BlockBehaviour.Properties.copy(Blocks.ICE).mapColor(MapColor.CLAY)));
    public static final RegistryObject<Block> PACKED_VOLCANIC_ICE = registerBlock("packed_volcanic_ice", () ->
            new PackedVolcanicIceBlock(BlockBehaviour.Properties.copy(Blocks.ICE).mapColor(MapColor.CLAY).randomTicks()));

    public static final RegistryObject<Block> GLOOM_FUNGUS_STEM = registerBlock("gloom_fungus_stem", () ->
            new GloomStemBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM).mapColor((blockState) -> {return blockState.getValue(GloomStemBlock.LIT) ? MapColor.COLOR_CYAN : MapColor.COLOR_LIGHT_GREEN;})));

    public static final RegistryObject<Block> GLOOM_FUNGUS_HYPHAE = registerBlock("gloom_fungus_hyphae", () ->
            new BaseGloomStemBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE).mapColor((blockState) -> {return blockState.getValue(GloomStemBlock.LIT) ? MapColor.COLOR_CYAN : MapColor.COLOR_LIGHT_GREEN;})));



     public static final RegistryObject<Block> GLOOM_FUNGUS_CAP = registerBlock("gloom_fungus_cap", () ->
            new GloomCapBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_WART_BLOCK).mapColor(MapColor.SAND).lightLevel((p_152607_) -> {return 8;})));
    public static final RegistryObject<Block> GLOOM_FUNGUS = registerBlock("gloom_fungus", () ->
            new GloomFungusBlock(new HugeGloomFungusGrower(), BlockBehaviour.Properties.copy(Blocks.RED_MUSHROOM).mapColor(MapColor.SAND).lightLevel((p_152607_) -> {return 8;})));
    public static final RegistryObject<Block> GLOOMY_FIDDLEHEAD = registerBlock("gloomy_fiddlehead", () ->
            new GloomyFiddleheadBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.ROOTS).pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ)));
    public static final RegistryObject<Block> TALL_GLOOMY_FIDDLEHEAD = registerBlock("tall_gloomy_fiddlehead", () ->
            new TallGloomyFiddleheadBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noCollission().instabreak().sound(SoundType.ROOTS).pushReaction(PushReaction.DESTROY).offsetType(BlockBehaviour.OffsetType.XZ)));

    public static final RegistryObject<Block> DROWSY_VINES = registerBlock("drowsy_vines", () ->
            new DrowsyVinesBlock(BlockBehaviour.Properties.copy(Blocks.WEEPING_VINES).mapColor((blockState) -> {return blockState.getValue(DrowsyVinesBlock.HEAD) ? MapColor.SAND : MapColor.COLOR_LIGHT_GREEN;})));


    public static final RegistryObject<Block> POTTED_FRIGHT_FUNGUS = registerBlockWithoutItem("potted_fright_fungus", () ->
            new FlowerPotBlock(FRIGHT_FUNGUS.get(), (BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_FUNGUS))));
    public static final RegistryObject<Block> POTTED_EMBER_ROOTS = registerBlockWithoutItem("potted_ember_roots", () ->
            new FlowerPotBlock(EMBER_ROOTS.get(), (BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_ROOTS))));
    public static final RegistryObject<Block> POTTED_FRIGHT_ROOTS = registerBlockWithoutItem("potted_fright_roots", () ->
            new FlowerPotBlock(FRIGHT_ROOTS.get(), (BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_ROOTS))));
    public static final RegistryObject<Block> POTTED_GLOOM_FUNGUS = registerBlockWithoutItem("potted_gloom_fungus", () ->
            new FlowerPotBlock(GLOOM_FUNGUS.get(), (BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_FUNGUS))));
    public static final RegistryObject<Block> POTTED_GLOOMY_FIDDLEHEAD = registerBlockWithoutItem("potted_gloomy_fiddlehead", () ->
            new FlowerPotBlock(GLOOMY_FIDDLEHEAD.get(), (BlockBehaviour.Properties.copy(Blocks.POTTED_WARPED_ROOTS))));




    public static final RegistryObject<Block> CHARRED_SOULROOT_CRATE = registerCompatBlock("charred_soulroot_crate", () ->
            new Block(BlockBehaviour.Properties.copy(FRIGHT_PLANKS.get()).strength(2.0F)), CompatUtil.FARMERSDELIGHT);
    public static final RegistryObject<Block> SOULROOT_CRATE = registerCompatBlock("soulroot_crate", () ->
            new Block(BlockBehaviour.Properties.copy(FRIGHT_PLANKS.get()).strength(2.0F)), CompatUtil.FARMERSDELIGHT);

    public static final RegistryObject<Block> FROZEN_SOULROOT_CRATE = registerCompatBlock("frozen_soulroot_crate", () ->
            new Block(BlockBehaviour.Properties.copy(FRIGHT_PLANKS.get()).strength(2.0F)), CompatUtil.FARMERSDELIGHT);

    public static final RegistryObject<Block> FROSTBITTEN_SOULROOT_CRATE = registerCompatBlock("frostbitten_soulroot_crate", () ->
            new Block(BlockBehaviour.Properties.copy(FRIGHT_PLANKS.get()).strength(2.0F)), CompatUtil.FARMERSDELIGHT);




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCK.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutItem(String name, Supplier<T> block) {
        return BLOCK.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> registerCompatBlock(String name, Supplier<T> block, String modId) {
        RegistryObject<T> toReturn = BLOCK.register(name, block);
        if (FMLLoader.getLoadingModList().getModFileById(modId) != null) {
            SoulfulItems.ITEM.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        }
        return toReturn;
    }
    public static <T extends Block> RegistryObject<T> registerCompatBlockItem(String name, Supplier<T> block, Item.Properties properties, String modId) {
        RegistryObject<T> toReturn = BLOCK.register(name, block);
        if (FMLLoader.getLoadingModList().getModFileById(modId) != null) {
            SoulfulItems.ITEM.register(name, () -> new BlockItem(toReturn.get(), properties));
        }
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return SoulfulItems.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}