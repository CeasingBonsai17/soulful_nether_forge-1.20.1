package net.bon.soulfulnether.state.property;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SoulfulProperties {
    public static final IntegerProperty MARSHMARROWS = IntegerProperty.create("marshmarrows", 1, 3);
    public static final BooleanProperty TOASTY = BooleanProperty.create("toasty");
}