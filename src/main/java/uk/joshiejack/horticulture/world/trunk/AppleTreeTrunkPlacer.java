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

public class AppleTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<AppleTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, AppleTreeTrunkPlacer::new));

    public AppleTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureWorld.APPLE_TREE_TRUNK_PLACER;
    }

    private void placeBranches(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks,
                               @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config, int width, Direction dir) {
        for (int i = 0; i <= width; i++) {
            place(reader, random, target, target.relative(dir, i), trunkBlocks, bounding, config);
            if (i == width) {
                placeLog(reader, random, target.relative(dir, i).above(), trunkBlocks, bounding, config);
                place(reader, random, target.relative(dir, i).relative(dir.getOpposite(), 1), target.relative(dir, i).relative(dir.getOpposite(), 1).relative(dir.getCounterClockWise()), trunkBlocks, bounding, config);
            }
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, int trunkHeight,
                                                  @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks, @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config) {
        int width = 3 + random.nextInt(3);
        boolean upper = random.nextBoolean();
        if (trunkHeight %2 == 0) {
            placeBranches(reader, random, target.above(trunkHeight).below(trunkHeight %4 == 0 ? 1 : 0), trunkBlocks, bounding, config, width, Direction.NORTH);
            placeBranches(reader, random, target.above(trunkHeight).below(trunkHeight %4 == 0 ? 0 : 1), trunkBlocks, bounding, config, width, Direction.SOUTH);
        } else {
            placeBranches(reader, random, target.above(trunkHeight).below(trunkHeight %3 == 0 ? 1 : 0), trunkBlocks, bounding, config, width, Direction.EAST);
            placeBranches(reader, random, target.above(trunkHeight).below(trunkHeight %3 == 0 ? 0 : 1), trunkBlocks, bounding, config, width, Direction.WEST);
        }
        return super.placeTrunk(reader, random, trunkHeight, target, trunkBlocks, bounding, config);
    }
}
