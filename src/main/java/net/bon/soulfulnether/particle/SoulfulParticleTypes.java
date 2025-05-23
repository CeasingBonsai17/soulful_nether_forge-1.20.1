package net.bon.soulfulnether.particle;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SoulfulNether.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FRIGHT_EMBER = PARTICLE_TYPES.register("fright_ember", () -> new SimpleParticleType(false){});
    public static final RegistryObject<SimpleParticleType> FRIGHT_SPORE = PARTICLE_TYPES.register("fright_spore", () -> new SimpleParticleType(false){});
    public static final RegistryObject<SimpleParticleType> ASHEN_SNOW = PARTICLE_TYPES.register("ashen_snow", () -> new SimpleParticleType(false){});
    public static final RegistryObject<SimpleParticleType> FALLING_GLOOMY_RAIN = PARTICLE_TYPES.register("falling_gloomy_rain", () -> new SimpleParticleType(false){});
    public static final RegistryObject<SimpleParticleType> LANDING_GLOOMY_RAIN = PARTICLE_TYPES.register("landing_gloomy_rain", () -> new SimpleParticleType(false){});
    public static final RegistryObject<SimpleParticleType> GLOOMY_FOG = PARTICLE_TYPES.register("gloomy_fog", () -> new SimpleParticleType(false){});


    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}