package uk.joshiejack.horticulture.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.penguinlib.item.base.AbstractWateringCanItem;

public class WateringCanItem extends AbstractWateringCanItem {
    public WateringCanItem() {
        super(new Item.Properties().stacksTo(1).tab(Horticulture.TAB), 64);
    }

    @Override
    protected void applyBonemealEffect(World world, BlockPos pos, PlayerEntity player, ItemStack itemstack, Hand hand) {
        if (world.random.nextInt(16) == 0 && applyBonemeal(itemstack, world, pos, player)) {
            if (!world.isClientSide)
                world.levelEvent(2005, pos, 0);
        }
    }

    private boolean applyBonemeal(ItemStack stack, World world, BlockPos pos, PlayerEntity player) {
        BlockState blockstate = world.getBlockState(pos);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, world, pos, blockstate, stack);
        if (hook != 0) return hook > 0;
        if (blockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable)blockstate.getBlock();
            if (igrowable.isValidBonemealTarget(world, pos, blockstate, world.isClientSide)) {
                if (world instanceof ServerWorld) {
                    if (igrowable.isBonemealSuccess(world, world.random, pos, blockstate)) {
                        igrowable.performBonemeal((ServerWorld)world, world.random, pos, blockstate);
                    }
                }

                return true;
            }
        }

        return false;
    }
}
