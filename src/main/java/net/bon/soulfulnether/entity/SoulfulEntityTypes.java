package net.bon.soulfulnether.entity;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.type.AshenSnowball;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.bon.soulfulnether.entity.type.Wallow;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SoulfulNether.MOD_ID);

    public static final RegistryObject<EntityType<Wallow>> WALLOW = ENTITY_TYPE.register("wallow", () ->
            EntityType.Builder.of(Wallow::new, MobCategory.MONSTER).fireImmune().sized(0.6f, 0.6f).build("wallow"));
    public static final RegistryObject<EntityType<BoneWyrm>> BONE_WYRM = ENTITY_TYPE.register("bone_wyrm", () ->
            EntityType.Builder.of(BoneWyrm::new, MobCategory.CREATURE).fireImmune().sized(1.7f, 0.8f).build("bone_wyrm"));
    public static final RegistryObject<EntityType<AshenSnowball>> ASHEN_SNOWBALL = ENTITY_TYPE.register("ashen_snowball", () ->
            EntityType.Builder.<AshenSnowball>of(AshenSnowball::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("ashen_snowball"));

    public static void init(IEventBus eventBus) {
        ENTITY_TYPE.register(eventBus);
    }
}