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

public class NewOrangeTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<NewOrangeTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, NewOrangeTreeFoliagePlacer::new));

    public NewOrangeTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
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
        int extraBranches = trunkHeight - 7;
        int width = Math.max(2, trunkHeight / 2) + 3;
        int branch = foliageHeight - (trunkHeight / 2);
        if (trunkHeight % 2 == 1) branch++;
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(5), width - 3, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(4), width - 1, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(3), width, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(2), width + 1, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above(), width + 2, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos(), width, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(), width - 1, branch, foliage.doubleTrunk());
        placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(2), width -2, branch, foliage.doubleTrunk());

        for (int i = 0; i < extraBranches; i++) {
            if (i % 2 == 0) {
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above((i + 1) * 2).above(), 1 + width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above((i + 1) * 2), 2 + width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above((i + 1) * 2).below(), width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().above((i + 1) * 2).below(2), width - (i + 1) - 2, branch, foliage.doubleTrunk());
            } else {
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(i * 2).above(), 1 + width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(i * 2), 2 + width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(i * 2).below(), width - (i + 1), branch, foliage.doubleTrunk());
                placeLeavesRow(world, pBlockSetter, random, config, foliage.pos().below(i * 2).below(2), width - (i + 1) - 1, branch, foliage.doubleTrunk());
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
        return distance > ((4 * radius)) + 2 || (ew == ns && random.nextInt(3) == 0);
    }
}
