package uk.joshiejack.horticulture.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import uk.joshiejack.penguinlib.event.PenguinEventHooks;
import uk.joshiejack.penguinlib.world.item.AbstractWateringCanItem;

public class WateringCanItem extends AbstractWateringCanItem {
    public WateringCanItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public boolean water(Player player, Level level, BlockPos pos, ItemStack stack, InteractionHand hand) {
        if (level.random.nextInt(16) == 0 && PenguinEventHooks.applyBonemeal(stack, level, pos, player)) {
            if (!level.isClientSide)
                level.levelEvent(2005, pos, 0);
            return true;
        }

        return super.water(player, level, pos, stack, hand);
    }
}
