package net.bon.soulfulnether.pack;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModFileInfo;

import java.nio.file.Path;

public class SoulfulBuiltInPacks {
    public static void dpRubinatedNetherCompat(AddPackFindersEvent event) {
        IModFileInfo mod = ModList.get().getModFileById(SoulfulNether.MOD_ID);
        Path file = mod.getFile().findResource("resourcepacks/rubinated_nether_compat");
        event.addRepositorySource((packConsumer) ->
                packConsumer.accept(
                        Pack.create("rubinated_nether_compat",
                                Component.literal("Soulful Nether + Rubinated Nether"),
                                true,
                                (path) -> new PathPackResources(path, file, true),
                                new Pack.Info(Component.literal("Compatibility for Rubinated Nether"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), FeatureFlagSet.of()),
                                PackType.SERVER_DATA, Pack.Position.TOP, true, PackSource.BUILT_IN)));
    }
    public static void dpNetherExpCompat(AddPackFindersEvent event) {
        IModFileInfo mod = ModList.get().getModFileById(SoulfulNether.MOD_ID);
        Path file = mod.getFile().findResource("resourcepacks/netherexp_compat");
        event.addRepositorySource((packConsumer) ->
                packConsumer.accept(
                        Pack.create("netherexp_compat",
                                Component.literal("Soulful Nether + Nether Exp"),
                                true,
                                (path) -> new PathPackResources(path, file, true),
                                new Pack.Info(Component.literal("Compatibility for Nether Exp"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), FeatureFlagSet.of()),
                                PackType.SERVER_DATA, Pack.Position.TOP, true, PackSource.BUILT_IN)));
    }
    public static void dpFarmersDelightCompat(AddPackFindersEvent event) {
        IModFileInfo mod = ModList.get().getModFileById(SoulfulNether.MOD_ID);
        Path file = mod.getFile().findResource("resourcepacks/farmersdelight_compat");
        event.addRepositorySource((packConsumer) ->
                packConsumer.accept(
                        Pack.create("farmersdelight_compat",
                                Component.literal("Soulful Nether + Farmer's Delight"),
                                true,
                                (path) -> new PathPackResources(path, file, true),
                                new Pack.Info(Component.literal("Compatibility for Farmer's Delight"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), FeatureFlagSet.of()),
                                PackType.SERVER_DATA, Pack.Position.TOP, true, PackSource.BUILT_IN)));
    }
    public static void dpSpeciesCompat(AddPackFindersEvent event) {
        IModFileInfo mod = ModList.get().getModFileById(SoulfulNether.MOD_ID);
        Path file = mod.getFile().findResource("resourcepacks/species_compat");
        event.addRepositorySource((packConsumer) ->
                packConsumer.accept(
                        Pack.create("species_compat",
                                Component.literal("Soulful Nether + Species"),
                                true,
                                (path) -> new PathPackResources(path, file, true),
                                new Pack.Info(Component.literal("Compatibility for Species"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), FeatureFlagSet.of()),
                                PackType.SERVER_DATA, Pack.Position.TOP, true, PackSource.BUILT_IN)));
    }
    public static void dpGalosphereCompat(AddPackFindersEvent event) {
        IModFileInfo mod = ModList.get().getModFileById(SoulfulNether.MOD_ID);
        Path file = mod.getFile().findResource("resourcepacks/galosphere_compat");
        event.addRepositorySource((packConsumer) ->
                packConsumer.accept(
                        Pack.create("galosphere_compat",
                                Component.literal("Soulful Nether + Galosphere"),
                                true,
                                (path) -> new PathPackResources(path, file, true),
                                new Pack.Info(Component.literal("Compatibility for Galosphere"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), FeatureFlagSet.of()),
                                PackType.SERVER_DATA, Pack.Position.TOP, true, PackSource.BUILT_IN)));
    }
}
