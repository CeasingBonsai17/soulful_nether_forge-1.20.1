package net.bon.soulfulnether.item.type;

import net.bon.soulfulnether.entity.SoulfulEntityTypes;
import net.bon.soulfulnether.util.SoulfulTags;
import net.bon.soulfulnether.world.entity.SoulfulEntityType;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MarshmarrowStickItem extends Item {
    public MarshmarrowStickItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            Entity entity = player.getControlledVehicle();
            if (player.isPassenger() && entity instanceof ItemSteerable steerable) {
                if (entity.getType() == SoulfulEntityTypes.BONE_WYRM.get() && steerable.boost()) {
                    stack.hurtAndBreak(25, player, (p_41312_) -> {
                        p_41312_.broadcastBreakEvent(hand);
                    });
                    if (stack.isEmpty()) {
                        ItemStack item = new ItemStack(Items.FISHING_ROD);
                        item.setTag(stack.getTag());
                        return InteractionResultHolder.success(item);
                    }

                    return InteractionResultHolder.success(stack);
                }
            }

            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResultHolder.pass(stack);
    }
}
