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

public class AppleTreeTrunkPlacer extends AbstractTrunkPlacer {
    public static final Codec<AppleTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, AppleTreeTrunkPlacer::new));

    public AppleTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureLevel.APPLE_TREE_TRUNK_PLACER.get();
    }

    private void placeBranches(@Nonnull LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, @Nonnull BlockPos target,
                               @Nonnull TreeConfiguration config, int width, Direction dir) {
        for (int i = 0; i <= width; i++) {
            place(reader, pBlockSetter, random, target, target.relative(dir, i), config);
            if (i == width) {
                placeLog(reader, pBlockSetter, random, target.relative(dir, i).above(), config);
                place(reader, pBlockSetter, random, target.relative(dir, i).relative(dir.getOpposite(), 1), target.relative(dir, i).relative(dir.getOpposite(), 1).relative(dir.getCounterClockWise()), config);
            }
        }
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(@Nonnull LevelSimulatedReader reader, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, int trunkHeight,
                                                            @Nonnull BlockPos target, @Nonnull TreeConfiguration config) {
        int width = random.nextInt(3);
        if (trunkHeight %2 == 0) {
            placeBranches(reader, pBlockSetter, random, target.above(trunkHeight).below(trunkHeight %4 == 0 ? 1 : 0), config, width, Direction.NORTH);
            placeBranches(reader, pBlockSetter, random, target.above(trunkHeight).below(trunkHeight %4 == 0 ? 0 : 1), config, width, Direction.SOUTH);
        } else {
            placeBranches(reader, pBlockSetter, random, target.above(trunkHeight).below(trunkHeight %3 == 0 ? 1 : 0), config, width, Direction.EAST);
            placeBranches(reader, pBlockSetter, random, target.above(trunkHeight).below(trunkHeight %3 == 0 ? 0 : 1), config, width, Direction.WEST);
        }
        return super.placeTrunk(reader, pBlockSetter, random, trunkHeight, target, config);
    }
}
