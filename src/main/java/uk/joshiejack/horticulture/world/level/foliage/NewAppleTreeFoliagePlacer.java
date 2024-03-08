package uk.joshiejack.horticulture.world.level.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.HorticultureLevel;

public class NewAppleTreeFoliagePlacer extends BlobFoliagePlacer {
    public static final Codec<NewAppleTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, NewAppleTreeFoliagePlacer::new));

    public NewAppleTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset, 5);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return HorticultureLevel.BANANA_TREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader world,
                                 @NotNull FoliageSetter pBlockSetter,
                                 @NotNull RandomSource random,
                                 @NotNull TreeConfiguration config,
                                 int trunkHeight,
                                 @NotNull FoliageAttachment foliage,
                                 int foliageHeight,
                                 int pFoliageRadius,
                                 int pOffset) {
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), 2, foliageHeight + 1, foliage.doubleTrunk());
        int width = random.nextInt(3);
        if (trunkHeight %2 == 0) {
            for (int i = -3; i <= 1; i++) {
                int j = Math.max(radius.sample(random) + foliage.radiusOffset() - 1 - i / 2, 1);
                for (int k = 1; k <= width + 1; k++) {
                    placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().relative(Direction.NORTH, k).above(trunkHeight % 4 == 0 ? 2 : 0), j, foliageHeight + i, foliage.doubleTrunk());
                    placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().relative(Direction.SOUTH, k).above(trunkHeight % 4 == 0 ? 0 : 2), j, foliageHeight + i, foliage.doubleTrunk());
                }
            }

        } else {
            for (int i = -3; i <= 1; i++) {
                int j = Math.max(radius.sample(random) + foliage.radiusOffset() - 1 - i / 2, 1);
                for (int k = 1; k <= width + 1; k++) {
                    placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().relative(Direction.WEST, k).above(trunkHeight % 3 == 0 ? 2 : 0), j, foliageHeight + i, foliage.doubleTrunk());
                    placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().relative(Direction.EAST, k).above(trunkHeight % 3 == 0 ? 0 : 2), j, foliageHeight + i, foliage.doubleTrunk());
                }
            }
        }
    }

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int ns, int y, int ew, int radius, boolean giantTrunk) {
        BlockPos centre = BlockPos.ZERO;
        BlockPos leaves = new BlockPos(ns, 0, ew);
        int distance = (int) centre.distSqr(leaves);
        return distance > ((4 * radius)) || (ew == ns && random.nextInt(3) == 0) || super.shouldSkipLocation(random, ns, y, ew, radius, giantTrunk);
    }
}
