package uk.joshiejack.horticulture.world.level.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.HorticultureLevel;

public class NewBananaTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<NewBananaTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, NewBananaTreeFoliagePlacer::new));

    public NewBananaTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return HorticultureLevel.BANANA_TREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader world,
                                 FoliagePlacer.@NotNull FoliageSetter pBlockSetter,
                                 @NotNull RandomSource random,
                                 @NotNull TreeConfiguration config,
                                 int pMaxFreeTreeHeight,
                                 FoliagePlacer.@NotNull FoliageAttachment foliage,
                                 int foliageHeight,
                                 int pFoliageRadius,
                                 int pOffset) {
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(), 0, foliageHeight, foliage.doubleTrunk());
        int count = 0;
        for (Direction dir: Direction.Plane.HORIZONTAL) {
            placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below().relative(dir, 2), 0, foliageHeight, foliage.doubleTrunk());
            placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(2).relative(dir, 3), 0, foliageHeight, foliage.doubleTrunk());
            if (random.nextBoolean()) {
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below().relative(dir, 2).relative(dir.getCounterClockWise(), 2), 0, foliageHeight, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(2).relative(dir, 3).relative(dir.getCounterClockWise(), 2), 0, foliageHeight, foliage.doubleTrunk());
            }

            if (random.nextBoolean() && count < 5) {
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above().relative(dir), 0, foliageHeight, foliage.doubleTrunk());
                count++;
                for (Direction direction: Direction.Plane.HORIZONTAL) {
                    if (random.nextBoolean()) {
                        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above().relative(dir).relative(direction), 0, foliageHeight, foliage.doubleTrunk());
                        count++;

                        if (count > 5)
                            break;
                    }
                }
            }
        }

        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), 1, foliageHeight, foliage.doubleTrunk());
    }

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int ns, int y, int ew, int radius, boolean giantTrunk) {
        return false;
    }
}
