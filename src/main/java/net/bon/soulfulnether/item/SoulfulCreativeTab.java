package net.bon.soulfulnether.item;

import net.bon.soulfulnether.SoulfulNether;
import net.bon.soulfulnether.block.SoulfulBlocks;
import net.bon.soulfulnether.util.CompatUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class  SoulfulCreativeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SoulfulNether.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SOULFULNETHER = CREATIVE_MODE_TAB.register("tutorial_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(SoulfulItems.SOULROOT.get()))
                    .title(Component.translatable("creativetab.soulfulnether"))
                    .displayItems((parameters, output) -> {
                        output.accept(SoulfulBlocks.FRIGHT_STEM.get());
                        output.accept(SoulfulBlocks.FRIGHT_HYPHAE.get());
                        output.accept(SoulfulBlocks.STRIPPED_FRIGHT_STEM.get());
                        output.accept(SoulfulBlocks.STRIPPED_FRIGHT_HYPHAE.get());
                        output.accept(SoulfulBlocks.FRIGHT_PLANKS.get());
                        output.accept(SoulfulBlocks.FRIGHT_STAIRS.get());
                        output.accept(SoulfulBlocks.FRIGHT_SLAB.get());
                        output.accept(SoulfulBlocks.FRIGHT_FENCE.get());
                        output.accept(SoulfulBlocks.FRIGHT_FENCE_GATE.get());
                        output.accept(SoulfulBlocks.FRIGHT_DOOR.get());
                        output.accept(SoulfulBlocks.FRIGHT_TRAPDOOR.get());
                        output.accept(SoulfulBlocks.FRIGHT_PRESSURE_PLATE.get());
                        output.accept(SoulfulBlocks.FRIGHT_BUTTON.get());
                        output.accept(SoulfulBlocks.LICHOSS_BLOCK.get());
                        output.accept(SoulfulBlocks.HANGING_LICHOSS.get());
                        output.accept(SoulfulBlocks.LICHOSS_CARPET.get());
                        output.accept(SoulfulBlocks.EMBER_ROOTS.get());
                        output.accept(SoulfulBlocks.FRIGHT_WART_BLOCK.get());
                        output.accept(SoulfulBlocks.FRIGHT_FUNGUS.get());
                        output.accept(SoulfulBlocks.FRIGHT_ROOTS.get());
                        output.accept(SoulfulItems.FRIGHT_SIGN.get());
                        output.accept(SoulfulItems.FRIGHT_HANGING_SIGN.get());
                        output.accept(SoulfulBlocks.SPIRALING_VINES.get());
                        output.accept(SoulfulItems.SOULROOT_SEEDS.get());
                        output.accept(SoulfulItems.SOULROOT.get());
                        if (CompatUtil.checkFarmersDelight()) {
                            output.accept(SoulfulBlocks.SOULROOT_CRATE.get());
                        }
                        output.accept(SoulfulItems.CHARRED_SOULROOT.get());
                        if (CompatUtil.checkFarmersDelight()) {
                            output.accept(SoulfulBlocks.CHARRED_SOULROOT_CRATE.get());
                        }
                        if (CompatUtil.checkRubinatedNether()) {
                            output.accept(SoulfulItems.FROZEN_SOULROOT.get());
                        }
                        if (CompatUtil.checkFarmersDelight() && CompatUtil.checkRubinatedNether()) {
                            output.accept(SoulfulBlocks.FROZEN_SOULROOT_CRATE.get());
                        }
                        if (CompatUtil.checkNetherExp()) {
                            output.accept(SoulfulItems.FROSTBITTEN_SOULROOT.get());
                        }
                        if (CompatUtil.checkFarmersDelight() && CompatUtil.checkNetherExp()) {
                            output.accept(SoulfulBlocks.FROSTBITTEN_SOULROOT_CRATE.get());
                        }
                        if (CompatUtil.checkNetherExp()) {
                            output.accept(SoulfulItems.FROSTBITTEN_POPSICLE.get());
                        }
                        output.accept(SoulfulItems.SOULROOT_PIE.get());
                        output.accept(SoulfulItems.MARSHMARROW.get());
                        output.accept(SoulfulBlocks.MARSHMARROW_STACK.get());
                        output.accept(SoulfulItems.TOASTY_MARSHMARROW.get());
                        output.accept(SoulfulBlocks.TOASTY_MARSHMARROW_STACK.get());
                        output.accept(SoulfulItems.ROASTED_MARSHMARROW.get());
                        output.accept(SoulfulBlocks.ROASTED_MARSHMARROW_STACK.get());
                        output.accept(SoulfulItems.MARSHMARROW_HEART.get());
                        if (CompatUtil.checkRubinatedNether()) {
                            output.accept(SoulfulItems.MARSHMARROW_MOCHI.get());
                        }
                        output.accept(SoulfulBlocks.ASHEN_SNOW_BLOCK.get());
                        output.accept(SoulfulBlocks.ASHEN_SNOW_LAYER.get());
                        output.accept(SoulfulItems.ASHEN_SNOW.get());
                        output.accept(SoulfulBlocks.ASHEN_BASALT.get());
                        output.accept(SoulfulBlocks.POLISHED_ASHEN_BASALT.get());
                        output.accept(SoulfulBlocks.VOLCANIC_QUARTZ_BLOCK.get());
                        output.accept(SoulfulBlocks.VOLCANIC_QUARTZ_BRICKS.get());
                        output.accept(SoulfulBlocks.POLISHED_VOLCANIC_QUARTZ.get());





                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}