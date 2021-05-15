package uk.joshiejack.horticulture.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import uk.joshiejack.horticulture.world.HorticultureWorld;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PeachTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<PeachTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, PeachTreeTrunkPlacer::new));

    public PeachTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureWorld.PEACH_TREE_TRUNK_PLACER;
    }

    private void placeBranches(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks,
                               @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config, int height, int branch, Direction dir) {
        BlockPos branchY = target.above(branch).below(2);
        placeLog(reader, random, branchY.relative(dir, 1), trunkBlocks, bounding, config);
        for (int i = 0; i <= height; i++) {
            int horizontalOffset = (i/2 + 2);
            BlockPos branchPos = branchY.relative(dir, horizontalOffset).above(i);
            placeLog(reader, random, branchPos, trunkBlocks, bounding, config);
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, int trunkHeight,
                                                  @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks, @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config) {
        boolean ew = random.nextBoolean();
        for (Direction dir: Direction.Plane.HORIZONTAL) {
            if ((ew && (dir == Direction.EAST || dir == Direction.WEST)) || (!ew && (dir == Direction.NORTH || dir == Direction.SOUTH)))
                placeBranches(reader, random, target.above(), trunkBlocks, bounding, config, trunkHeight/2 + 1, trunkHeight, dir);
            else
                placeBranches(reader, random, target, trunkBlocks, bounding, config, trunkHeight/2 + 1, trunkHeight, dir);
        }

        return super.placeTrunk(reader, random, 3 + trunkHeight * 2, target, trunkBlocks, bounding, config);
    }
}
