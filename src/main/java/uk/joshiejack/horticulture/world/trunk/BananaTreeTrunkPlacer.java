package uk.joshiejack.horticulture.world.trunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import uk.joshiejack.horticulture.world.HorticultureWorld;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BananaTreeTrunkPlacer extends StraightTrunkPlacer {
    public static final Codec<BananaTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((type) -> trunkPlacerParts(type).apply(type, BananaTreeTrunkPlacer::new));

    public BananaTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Nonnull
    @Override
    protected TrunkPlacerType<?> type() {
        return HorticultureWorld.BANANA_TREE_TRUNK_PLACER;
    }

    @Nonnull
    @Override
    public List<FoliagePlacer.Foliage> placeTrunk(@Nonnull IWorldGenerationReader reader, @Nonnull Random random, int trunkHeight,
                                                  @Nonnull BlockPos target, @Nonnull Set<BlockPos> trunkBlocks, @Nonnull MutableBoundingBox bounding, @Nonnull BaseTreeFeatureConfig config) {
        placeLog(reader, random, target.above(trunkHeight - 1).east(), trunkBlocks, bounding, config);
        placeLog(reader, random, target.above(trunkHeight - 1).west(), trunkBlocks, bounding, config);
        placeLog(reader, random, target.above(trunkHeight - 1).north(), trunkBlocks, bounding, config);
        placeLog(reader, random, target.above(trunkHeight - 1).south(), trunkBlocks, bounding, config);
        return super.placeTrunk(reader, random, trunkHeight, target, trunkBlocks, bounding, config);
    }
}
