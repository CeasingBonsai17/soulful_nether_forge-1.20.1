package net.bon.soulfulnether.world.entity;

import net.bon.soulfulnether.SoulfulNether;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoulfulEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SoulfulNether.MOD_ID);

    public static void init(IEventBus eventBus) {
        ENTITY.register(eventBus);
    }
}
