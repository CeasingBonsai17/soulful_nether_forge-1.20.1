package net.bon.soulfulnether.event;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.entity.type.BoneWyrm;
import net.bon.soulfulnether.entity.type.Wallow;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SoulfulNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulfulEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(SoulfulEntityTypes.WALLOW.get(), Wallow.createAttributes().build());
        event.put(SoulfulEntityTypes.BONE_WYRM.get(), BoneWyrm.createAttributes().build());
    }
}
