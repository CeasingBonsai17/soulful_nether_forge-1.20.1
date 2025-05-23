package net.bon.soulfulnether;


import net.bon.soulfulnether.particle.*;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.WaterDropParticle;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class SoulfulNetherClient {
    public static void register() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(SoulfulNetherClient::particles);
    }

    public static void particles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SoulfulParticleTypes.FRIGHT_EMBER.get(), SoulfulSuspendedParticle.FrightEmberProvider::new);
        event.registerSpriteSet(SoulfulParticleTypes.FRIGHT_SPORE.get(), SoulfulSuspendedParticle.FrightSporeProvider::new);
        event.registerSpriteSet(SoulfulParticleTypes.ASHEN_SNOW.get(), AshenSnowParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.FALLING_GLOOMY_RAIN.get(), SapFallAndLandParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.LANDING_GLOOMY_RAIN.get(), WaterDropParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.GLOOMY_FOG.get(), GloomyFogParticle.Provider::new);
    }
}