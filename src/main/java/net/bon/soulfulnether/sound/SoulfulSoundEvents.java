package net.bon.soulfulnether.sound;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.item.SoulfulItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoulfulSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, SoulfulNether.MOD_ID);

    public static final RegistryObject<SoundEvent> GLOOM_CAP_BREAK = registerSoundEvent("block.gloom_cap.break");
    public static final RegistryObject<SoundEvent> GLOOMY_AMBER_SQUISH = registerSoundEvent("block.gloomy_amber.squish");

    public static final RegistryObject<SoundEvent> BONE_WYRM_SADDLE = registerSoundEvent("entity.bone_wyrm.saddle");
    public static final RegistryObject<SoundEvent> BONE_WYRM_HURT = registerSoundEvent("entity.bone_wyrm.hurt");
    public static final RegistryObject<SoundEvent> BONE_WYRM_AMBIENT = registerSoundEvent("entity.bone_wyrm.ambient");
    public static final RegistryObject<SoundEvent> BONE_WYRM_DEATH = registerSoundEvent("entity.bone_wyrm.death");
    public static final RegistryObject<SoundEvent> BONE_WYRM_LARVA_DEATH = registerSoundEvent("entity.bone_wyrm_larva.death");

    public static final RegistryObject<SoundEvent> WALLOW_HURT = registerSoundEvent("entity.wallow.hurt");
    public static final RegistryObject<SoundEvent> WALLOW_AMBIENT = registerSoundEvent("entity.wallow.ambient");
    public static final RegistryObject<SoundEvent> WALLOW_DEATH = registerSoundEvent("entity.wallow.death");

    private static <T extends Block> RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(SoulfulNether.MOD_ID, name)));
    }

    public static void init(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
