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

public class PeachTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<PeachTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, PeachTreeTrunkPlacer::new));

    public PeachTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureLevel.PEACH_TREE_TRUNK_PLACER.get();
    }

    private void placeBranches(@Nonnull LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, @Nonnull BlockPos target,
                               @Nonnull TreeConfiguration config, int height, int branch, Direction dir) {
        BlockPos branchY = target.above(branch).below(2);
        placeLog(reader, pBlockSetter, random, branchY.relative(dir, 1), config);
        for (int i = 0; i <= height; i++) {
            int horizontalOffset = (i/2 + 2);
            BlockPos branchPos = branchY.relative(dir, horizontalOffset).above(i);
            placeLog(reader, pBlockSetter, random, branchPos, config);
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(@Nonnull LevelSimulatedReader reader, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, int trunkHeight,
                                                            @Nonnull BlockPos target, @Nonnull TreeConfiguration config) {
        boolean ew = random.nextBoolean();
        for (Direction dir: Direction.Plane.HORIZONTAL) {
            if ((ew && (dir == Direction.EAST || dir == Direction.WEST)) || (!ew && (dir == Direction.NORTH || dir == Direction.SOUTH)))
                placeBranches(reader, pBlockSetter, random, target.above(), config, trunkHeight/2 + 1, trunkHeight, dir);
            else
                placeBranches(reader, pBlockSetter, random, target, config, trunkHeight/2 + 1, trunkHeight, dir);
        }

        return super.placeTrunk(reader, pBlockSetter, random, 3 + trunkHeight * 2, target, config);
    }
}
