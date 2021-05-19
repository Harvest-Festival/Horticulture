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

public class OrangeTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<OrangeTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, OrangeTreeTrunkPlacer::new));

    public OrangeTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureWorld.ORANGE_TREE_TRUNK_PLACER;
    }

    private void placeBranches(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks,
                               @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config, int width, int branch) {
        BlockPos branchY = target.above(branch);
        placeLog(reader, random, branchY.relative(Direction.NORTH, width).relative(Direction.EAST, width).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.SOUTH, width).relative(Direction.EAST, width).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.SOUTH, width).relative(Direction.WEST, width).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.NORTH, width).relative(Direction.WEST, width).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.NORTH, width + 1).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.EAST, width + 1).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.SOUTH, width + 1).above(), trunkBlocks, bounding, config);
        placeLog(reader, random, branchY.relative(Direction.WEST, width + 1).above(), trunkBlocks, bounding, config);
        for (Direction dir: Direction.Plane.HORIZONTAL) {
            for (int i = 0; i <= width; i++) {
                BlockPos branchPos = branchY.relative(dir, i);
                place(reader, random, branchY, branchPos, trunkBlocks, bounding, config);
                if (i == width) {
                    for (int j = 1; j < width; j++) {
                        place(reader, random, branchPos, branchPos.relative(dir.getClockWise(), j), trunkBlocks, bounding, config);
                        place(reader, random, branchPos, branchPos.relative(dir.getCounterClockWise(), j), trunkBlocks, bounding, config);
                    }
                }
            }
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, int trunkHeight,
                                                  @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks, @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config) {
        int extraBranches = trunkHeight - 7;
        int width = Math.max(2, trunkHeight / 2);
        int branch = (trunkHeight / 2);
        if (trunkHeight %2 == 1) branch++;
        placeBranches(reader, random, target, trunkBlocks, bounding, config, width, branch);
        for (int i = 0; i < extraBranches; i++) {
            if (i %2 == 0) {
                placeBranches(reader, random, target.above((i + 1) * 2), trunkBlocks, bounding, config, width - (i + 1), branch);
            } else {
               placeBranches(reader, random, target.below(i * 2), trunkBlocks, bounding, config, width - (i + 1), branch);
            }
        }

        place(reader, random, target.above(trunkHeight), target.above(trunkHeight).east(), trunkBlocks, bounding, config);
        place(reader, random, target.above(trunkHeight), target.above(trunkHeight).west(), trunkBlocks, bounding, config);
        place(reader, random, target.above(trunkHeight), target.above(trunkHeight).north(), trunkBlocks, bounding, config);
        place(reader, random, target.above(trunkHeight), target.above(trunkHeight).south(), trunkBlocks, bounding, config);
        return super.placeTrunk(reader, random, trunkHeight, target, trunkBlocks, bounding, config);
    }
}
