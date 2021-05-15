package uk.joshiejack.horticulture.world.trunk;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.Set;

public abstract class AbstractTrunkPlacer extends StraightTrunkPlacer {
    public AbstractTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    protected void place(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, @Nonnull BlockPos original, @Nonnull BlockPos target,
                         @Nonnull Set<BlockPos> trunkBlocks, @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config) {
        if (TreeFeature.validTreePos(reader, target)) {
            setBlock(reader, target, config.trunkProvider.getState(random, target).setValue(RotatedPillarBlock.AXIS, getLogAxis(original, target)), bounding);
            trunkBlocks.add(target.immutable());
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
