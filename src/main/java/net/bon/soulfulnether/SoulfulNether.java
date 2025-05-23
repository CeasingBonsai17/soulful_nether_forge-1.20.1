package net.bon.soulfulnether;

import com.mojang.logging.LogUtils;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.effect.SoulfulEffects;
import net.bon.soulfulnether.item.SoulfulCreativeTab;
import net.bon.soulfulnether.item.SoulfulItems;
import net.bon.soulfulnether.pack.SoulfulBuiltInPacks;
import net.bon.soulfulnether.painting.SoulfulPaintings;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.util.CompatUtil;
import net.bon.soulfulnether.worldgen.SoulfulBiomes;
import net.bon.soulfulnether.worldgen.SoulfulSurfaceRules;
import net.bon.soulfulnether.worldgen.feature.SoulfulFeature;
import net.jadenxgamer.elysium_api.api.biome.ElysiumBiomeRegistry;
import net.jadenxgamer.elysium_api.api.surface_rules.SurfaceRulesRegistry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SoulfulNether.MOD_ID)
public class SoulfulNether
{
    public static final String MOD_ID = "soulfulnether";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SoulfulNether() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> SoulfulNetherClient::register);
        SoulfulBlocks.register(modEventBus);
        SoulfulItems.register(modEventBus);
        SoulfulPaintings.register(modEventBus);
        SoulfulCreativeTab.register(modEventBus);
        SoulfulFeature.register(modEventBus);
        SoulfulParticleTypes.register(modEventBus);
        SoulfulEffects.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(SoulfulNether::addBuiltInPacks);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRulesRegistry.registerSurfaceRule(SoulfulSurfaceRules.register());
            registerCompostables();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        RegistryAccess registryAccess = event.getServer().registryAccess();
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.SOUL_SAND_VALLEY, SoulfulBiomes.FRIGHT_FOREST, 0.2, 20,
                new ResourceLocation(MOD_ID, "soul_sand_vally1"), registryAccess);
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.BASALT_DELTAS, SoulfulBiomes.ASHEN_DELTAS, 0.2, 20,
                new ResourceLocation(MOD_ID, "basalt_deltas1"), registryAccess);
        ElysiumBiomeRegistry.replaceNetherBiome(Biomes.WARPED_FOREST, SoulfulBiomes.GLOOM_FOREST, 0.2, 16,
                new ResourceLocation(MOD_ID, "warped_forest1"), registryAccess);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }

    protected void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT_SEEDS.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.CHARRED_SOULROOT.get(), 0.85f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SOULROOT_PIE.get(), 1.0f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.LICHOSS_BLOCK.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.HANGING_LICHOSS.get(), 0.5f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.LICHOSS_CARPET.get(), 0.3f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.EMBER_ROOTS.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.FRIGHT_FUNGUS.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.FRIGHT_ROOTS.get(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(SoulfulItems.SPIRALING_VINES.get(), 0.5f);
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
