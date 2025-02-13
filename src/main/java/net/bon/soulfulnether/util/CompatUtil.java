package net.bon.soulfulnether.util;

import net.minecraftforge.fml.loading.FMLLoader;

public class CompatUtil {
    public static String RUBINATED_NETHER = "rubinated_nether";
    public static String SPECIES = "species";
    public static String GALOSPHERE = "galosphere";
    public static String NETHEREXP = "netherexp";
    public static String FARMERSDELIGHT = "farmersdelight";

    public static boolean checkRubinatedNether() {
        return FMLLoader.getLoadingModList().getModFileById(RUBINATED_NETHER) != null;
    }
    public static boolean checkSpecies() {
        return FMLLoader.getLoadingModList().getModFileById(SPECIES) != null;
    }
    public static boolean checkGalophere() {
        return FMLLoader.getLoadingModList().getModFileById(GALOSPHERE) != null;
    }
    public static boolean checkNetherExp() {
        return FMLLoader.getLoadingModList().getModFileById(NETHEREXP) != null;
    }
    public static boolean checkFarmersDelight() {
        return FMLLoader.getLoadingModList().getModFileById(FARMERSDELIGHT) != null;
    }
}
