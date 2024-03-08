package uk.joshiejack.horticulture.world.level.trunk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public abstract class AbstractTrunkPlacer extends StraightTrunkPlacer {
    public AbstractTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }


    protected void place(@Nonnull LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, @Nonnull BlockPos original, @Nonnull BlockPos target, @Nonnull TreeConfiguration config) {
        if (validTreePos(reader, target)) {
            pBlockSetter.accept(target, config.trunkProvider.getState(random, target).setValue(RotatedPillarBlock.AXIS, getLogAxis(original, target)));
        }
    }

    private Direction.Axis getLogAxis(BlockPos original, BlockPos target) {
        Direction.Axis direction$axis = Direction.Axis.Y;
        int i = Math.abs(target.getX() - original.getX());
        int j = Math.abs(target.getZ() - original.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                direction$axis = Direction.Axis.X;
            } else {
                direction$axis = Direction.Axis.Z;
            }
        }

        return direction$axis;
    }
}
