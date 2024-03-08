package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("ConstantConditions")
public class OldSprinklerBlockEntity extends AbstractSprinklerBlockEntity {
    public OldSprinklerBlockEntity(BlockPos pos, BlockState state) {
        super(HorticultureBlockEntities.OLD_SPRINKLER.get(), pos, state, 0.5D, 1);
    }

    @Override
    public double getRandomDouble() {
        return (level.random.nextDouble() - 0.5D) / 3;
    }
}
