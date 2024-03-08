package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("ConstantConditions")
public class IronSprinklerBlockEntity extends AbstractSprinklerBlockEntity {
    public IronSprinklerBlockEntity(BlockPos pos, BlockState state) {
        super(HorticultureBlockEntities.IRON_SPRINKLER.get(), pos, state,0.7D, 4);
    }

    @Override
    public double getRandomDouble() {
        return level.random.nextDouble() - 0.5D;
    }
}
