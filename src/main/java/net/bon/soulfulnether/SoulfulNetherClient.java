package net.bon.soulfulnether;


import net.bon.soulfulnether.particle.AshenSnowParticle;
import net.bon.soulfulnether.particle.SoulfulParticleTypes;
import net.bon.soulfulnether.particle.SoulfulSuspendedParticle;
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
    }
}