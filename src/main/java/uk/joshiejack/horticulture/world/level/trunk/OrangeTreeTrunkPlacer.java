package uk.joshiejack.horticulture.world.level.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.HorticultureLevel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiConsumer;

public class OrangeTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<OrangeTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, OrangeTreeTrunkPlacer::new));

    public OrangeTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureLevel.ORANGE_TREE_TRUNK_PLACER.get();
    }

    private void placeBranches(@Nonnull LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, @Nonnull BlockPos target,
                               @Nonnull TreeConfiguration config, int width, int branch) {
        BlockPos branchY = target.above(branch);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.NORTH, width).relative(Direction.EAST, width).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.SOUTH, width).relative(Direction.EAST, width).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.SOUTH, width).relative(Direction.WEST, width).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.NORTH, width).relative(Direction.WEST, width).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.NORTH, width + 1).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.EAST, width + 1).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.SOUTH, width + 1).above(), config);
        placeLog(reader, pBlockSetter, random, branchY.relative(Direction.WEST, width + 1).above(), config);
        for (Direction dir: Direction.Plane.HORIZONTAL) {
            for (int i = 0; i <= width; i++) {
                BlockPos branchPos = branchY.relative(dir, i);
                place(reader, pBlockSetter, random, branchY, branchPos, config);
                if (i == width) {
                    for (int j = 1; j < width; j++) {
                        place(reader, pBlockSetter, random, branchPos, branchPos.relative(dir.getClockWise(), j), config);
                        place(reader, pBlockSetter, random, branchPos, branchPos.relative(dir.getCounterClockWise(), j), config);
                    }
                }
            }
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(@Nonnull LevelSimulatedReader reader, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, int trunkHeight,
                                                            @Nonnull BlockPos target, @Nonnull TreeConfiguration config) {
        int extraBranches = trunkHeight - 7;
        int width = Math.max(2, trunkHeight / 2);
        int branch = (trunkHeight / 2);
        if (trunkHeight %2 == 1) branch++;
        placeBranches(reader, pBlockSetter, random, target, config, width, branch);
        for (int i = 0; i < extraBranches; i++) {
            if (i %2 == 0) {
                placeBranches(reader, pBlockSetter, random, target.above((i + 1) * 2), config, width - (i + 1), branch);
            } else {
               placeBranches(reader, pBlockSetter, random, target.below(i * 2), config, width - (i + 1), branch);
            }
        }

        place(reader, pBlockSetter, random, target.above(trunkHeight), target.above(trunkHeight).east(), config);
        place(reader, pBlockSetter, random, target.above(trunkHeight), target.above(trunkHeight).west(), config);
        place(reader, pBlockSetter, random, target.above(trunkHeight), target.above(trunkHeight).north(), config);
        place(reader, pBlockSetter, random, target.above(trunkHeight), target.above(trunkHeight).south(), config);
        return super.placeTrunk(reader, pBlockSetter, random, trunkHeight, target, config);
    }
}
