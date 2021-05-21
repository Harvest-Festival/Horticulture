package uk.joshiejack.horticulture.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import uk.joshiejack.horticulture.world.HorticultureWorld;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.Set;

public class AppleTreeFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<AppleTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, AppleTreeFoliagePlacer::new));

    public AppleTreeFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset, 5);
    }

    @Nonnull
    @Override
    protected FoliagePlacerType<?> type() {
        return HorticultureWorld.APPLE_TREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@Nonnull IWorldGenerationReader world, @Nonnull Random random, @Nonnull BaseTreeFeatureConfig config,
                                 int trunkHeight, @Nonnull Foliage foliage, int foliageHeight, int radius, @Nonnull Set<BlockPos> leaves, int offset, @Nonnull MutableBoundingBox boundingBox) {
        placeLeavesRow(world, random, config, foliage.foliagePos(), 2, leaves, foliageHeight + 1, foliage.doubleTrunk(), boundingBox);
        int width = random.nextInt(3);
        if (trunkHeight %2 == 0) {
            for (int i = -3; i <= 1; i++) {
                int j = Math.max(radius + foliage.radiusOffset() - 1 - i / 2, 1);
                for (int k = 1; k <= width + 1; k++) {
                    placeLeavesRow(world, random, config, foliage.foliagePos().relative(Direction.NORTH, k).above(trunkHeight % 4 == 0 ? 2 : 0), j, leaves, foliageHeight + i, foliage.doubleTrunk(), boundingBox);
                    placeLeavesRow(world, random, config, foliage.foliagePos().relative(Direction.SOUTH, k).above(trunkHeight % 4 == 0 ? 0 : 2), j, leaves, foliageHeight + i, foliage.doubleTrunk(), boundingBox);
                }
            }

        } else {
            for (int i = -3; i <= 1; i++) {
                int j = Math.max(radius + foliage.radiusOffset() - 1 - i / 2, 1);
                for (int k = 1; k <= width + 1; k++) {
                    placeLeavesRow(world, random, config, foliage.foliagePos().relative(Direction.WEST, k).above(trunkHeight % 3 == 0 ? 2 : 0), j, leaves, foliageHeight + i, foliage.doubleTrunk(), boundingBox);
                    placeLeavesRow(world, random, config, foliage.foliagePos().relative(Direction.EAST, k).above(trunkHeight % 3 == 0 ? 0 : 2), j, leaves, foliageHeight + i, foliage.doubleTrunk(), boundingBox);
                }
            }
        }
    }

    @Override
    public int foliageHeight(@Nonnull Random random, int trunkHeight, @Nonnull BaseTreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@Nonnull Random random, int ns, int y, int ew, int radius, boolean giantTrunk) {
        BlockPos centre = BlockPos.ZERO;
        BlockPos leaves = new BlockPos(ns, 0, ew);
        int distance = (int) centre.distSqr(leaves);
        return distance > ((4 * radius)) || (ew == ns && random.nextInt(3) == 0) || super.shouldSkipLocation(random, ns, y, ew, radius, giantTrunk);
    }
}
