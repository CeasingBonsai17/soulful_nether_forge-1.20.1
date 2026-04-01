package net.bon.soulfulnether.event;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.entity.client.BoneWyrmModel;
import net.bon.soulfulnether.entity.client.SoulfulModelLayers;
import net.bon.soulfulnether.entity.client.WallowModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SoulfulNether.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SoulfulClientEventBusEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SoulfulModelLayers.WALLOW, WallowModel::createBodyLayer);
        event.registerLayerDefinition(SoulfulModelLayers.BONE_WYRM, BoneWyrmModel::createBodyLayer);
        event.registerLayerDefinition(SoulfulModelLayers.BONE_WYRM_SADDLE, BoneWyrmModel::createBodyLayer);
    }
}
