package net.bon.soulfulnether.painting;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANT =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, SoulfulNether.MOD_ID);

    public static final RegistryObject<PaintingVariant> HELL_ROSE = PAINTING_VARIANT.register( "hell_rose", () ->
            new PaintingVariant(48, 48));



    public static void init(IEventBus eventBus) {
        PAINTING_VARIANT.register(eventBus);
    }
}
