package uk.joshiejack.horticulture.world.level.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.HorticultureLevel;

public class NewPeachTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<NewPeachTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, NewPeachTreeFoliagePlacer::new));

    public NewPeachTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
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
        int top = foliageHeight + 2;
        int bottom = foliageHeight - (trunkHeight + 10);
        if (trunkHeight %2 == 1) bottom++;
        int size = Math.abs(top - bottom);
        for (int r = 0; r <= size/2; r++) {
            placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), Math.min(r, size / 3 + 1), bottom + r, foliage.doubleTrunk());
            placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), Math.min(r, size / 3 + 1), top - r, foliage.doubleTrunk());
        }

        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), 1, foliageHeight, foliage.doubleTrunk());
    }

    @Override
    public int foliageHeight(@NotNull RandomSource pRandom, int pHeight, @NotNull TreeConfiguration pConfig) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource random, int ns, int y, int ew, int radius, boolean giantTrunk) {
        BlockPos centre = BlockPos.ZERO;
        BlockPos leaves = new BlockPos(ns, 0, ew);
        int distance = centre.distManhattan(leaves);
        return distance > radius || ew >=  radius - 1 || ns >=  radius - 1 || ((ew >= radius - 2 || ns >= radius - 2) && random.nextInt(3) == 1);    }
}
