package uk.joshiejack.horticulture.world.level.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.HorticultureLevel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiConsumer;

public class BananaTreeTrunkPlacer extends StraightTrunkPlacer {
    public static final Codec<BananaTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, BananaTreeTrunkPlacer::new));

    public BananaTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureLevel.BANANA_TREE_TRUNK_PLACER.get();
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(@Nonnull LevelSimulatedReader reader, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @Nonnull RandomSource random, int trunkHeight,
                                                            @Nonnull BlockPos target, @Nonnull TreeConfiguration config) {
        placeLog(reader, pBlockSetter, random, target.above(trunkHeight - 1).east(), config);
        placeLog(reader, pBlockSetter, random, target.above(trunkHeight - 1).west(), config);
        placeLog(reader, pBlockSetter, random, target.above(trunkHeight - 1).north(), config);
        placeLog(reader, pBlockSetter, random, target.above(trunkHeight - 1).south(), config);
        return super.placeTrunk(reader, pBlockSetter, random, trunkHeight, target, config);
    }
}
