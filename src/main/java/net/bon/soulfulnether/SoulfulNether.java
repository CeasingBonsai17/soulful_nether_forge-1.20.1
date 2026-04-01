package net.bon.soulfulnether;

import com.mojang.logging.LogUtils;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.effect.SoulfulEffects;
import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.entity.client.BoneWyrmRenderer;
import net.bon.soulfulnether.item.SoulfulCreativeTab;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.pack.SoulfulBuiltInPacks;
import net.bon.soulfulnether.painting.SoulfulPaintings;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.sound.SoulfulSoundEvents;
import net.bon.soulfulnether.util.CompatUtil;
import net.bon.soulfulnether.world.entity.SoulfulEntityType;
import net.bon.soulfulnether.worldgen.SoulfulBiomes;
import net.bon.soulfulnether.worldgen.SoulfulSurfaceRules;
import net.bon.soulfulnether.worldgen.feature.SoulfulFeature;
import net.jadenxgamer.elysium_api.api.biome.ElysiumBiomeRegistry;
import net.jadenxgamer.elysium_api.api.surface_rules.SurfaceRulesRegistry;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.Objects;

@Mod(SoulfulNether.MOD_ID)
public class SoulfulNether
{
    public static final String MOD_ID = "soulfulnether";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SoulfulNether() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> SoulfulNetherClient::init);
        SoulfulBlocks.init(modEventBus);
        SoulfulSoundEvents.init(modEventBus);
        SoulfulItems.init(modEventBus);
        SoulfulPaintings.init(modEventBus);
        SoulfulCreativeTab.init(modEventBus);
        SoulfulFeature.init(modEventBus);
        SoulfulParticleTypes.init(modEventBus);
        SoulfulEffects.init(modEventBus);
        SoulfulEntityType.init(modEventBus);
        SoulfulEntityTypes.init(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SoulfulNether::addBuiltInPacks);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRulesRegistry.registerNetherSurfaceRule(SoulfulSurfaceRules.register(), SoulfulNether.MOD_ID);
            registerCompostables();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public void growBoneWyrm(ItemExpireEvent event) {
        if (event.getEntity().getItem().is(SoulfulItems.BONE_WYRM_LARVA.get())) {
            event.getEntity().level().addFreshEntity(Objects.requireNonNull(SoulfulEntityTypes.BONE_WYRM.get().create(event.getEntity().level())));
        }
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        RegistryAccess registryAccess = event.getServer().registryAccess();
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.SOUL_SAND_VALLEY, SoulfulBiomes.FRIGHT_FOREST, 0.2, 20,
                new ResourceLocation(MOD_ID, "fright_forest"), registryAccess);
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.BASALT_DELTAS, SoulfulBiomes.ASHEN_DELTAS, 0.15, 20,
                new ResourceLocation(MOD_ID, "ashen_deltas"), registryAccess);
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.WARPED_FOREST, SoulfulBiomes.GLOOMY_GLADES, 0.1, 18,
                new ResourceLocation(MOD_ID, "gloomy_glades"), registryAccess);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            SoulfulNetherClient.registerEntityRenderers();
        }
    }

    protected void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.CHARRED_SOULROOT.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT_PIE.get(), 1.0f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.LICHOSS_BLOCK.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.HANGING_LICHOSS.get().asItem(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.LICHOSS_CARPET.get().asItem(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.EMBER_ROOTS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.FRIGHT_FUNGUS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.FRIGHT_ROOTS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.SPIRALING_VINES.get().asItem(), 0.5f);

        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.GLOOM_FUNGUS.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.GLOOMY_FIDDLEHEAD.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.TALL_GLOOMY_FIDDLEHEAD.get().asItem(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulBlocks.DROWSY_VINES.get().asItem(), 0.5f);
    }

    private static void addBuiltInPacks(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            if (CompatUtil.checkNetherExp()) {
                SoulfulBuiltInPacks.dpNetherExpCompat(event);
            }
            if (CompatUtil.checkSpecies()) {
                SoulfulBuiltInPacks.dpSpeciesCompat(event);
            }
            if (CompatUtil.checkGalophere()) {
                SoulfulBuiltInPacks.dpGalosphereCompat(event);
            }
            if (CompatUtil.checkRubinatedNether()) {
                SoulfulBuiltInPacks.dpRubinatedNetherCompat(event);
            }
            if (CompatUtil.checkFarmersDelight()) {
                SoulfulBuiltInPacks.dpFarmersDelightCompat(event);
            }
        }
    }
}
