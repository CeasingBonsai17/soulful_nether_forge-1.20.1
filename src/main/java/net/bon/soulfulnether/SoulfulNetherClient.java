package net.bon.soulfulnether;


import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.entity.client.BoneWyrmRenderer;
import net.bon.soulfulnether.entity.client.WallowRenderer;
import net.bon.soulfulnether.particle.*;
import net.bon.soulfulnether.particle.type.*;
import net.bon.soulfulnether.world.entity.SoulfulEntityType;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.WaterDropParticle;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class SoulfulNetherClient {
    public static void init() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(SoulfulNetherClient::particles);

    }


    public static void registerEntityRenderers() {
        EntityRenderers.register(SoulfulEntityTypes.ASHEN_SNOWBALL.get(), ThrownItemRenderer::new);
        EntityRenderers.register(SoulfulEntityTypes.BONE_WYRM.get(), BoneWyrmRenderer::new);
        EntityRenderers.register(SoulfulEntityTypes.WALLOW.get(), WallowRenderer::new);
    }

    public static void particles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(SoulfulParticleTypes.FRIGHT_EMBER.get(), SoulfulSuspendedParticle.FrightEmberProvider::new);
        event.registerSpriteSet(SoulfulParticleTypes.FRIGHT_SPORE.get(), SoulfulSuspendedParticle.FrightSporeProvider::new);
        event.registerSpriteSet(SoulfulParticleTypes.ASHEN_SNOW.get(), AshenSnowParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.FALLING_GLOOMY_RAIN.get(), SapFallAndLandParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.LANDING_GLOOMY_RAIN.get(), WaterDropParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.GLOOMY_FOG.get(), GloomyFogParticle.Provider::new);
        event.registerSpriteSet(SoulfulParticleTypes.DROWSY_DUST.get(), DrowsyDustParticle.Provider::new);
    }
}