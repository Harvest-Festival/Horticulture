package uk.joshiejack.horticulture.world.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import uk.joshiejack.horticulture.world.HorticultureWorld;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.Set;

public class OrangeTreeFoliagePlacer extends FoliagePlacer {
    public static final Codec<OrangeTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, OrangeTreeFoliagePlacer::new));

    public OrangeTreeFoliagePlacer(FeatureSpread radius, FeatureSpread offset) {
        super(radius, offset);
    }

    @Nonnull
    @Override
    protected FoliagePlacerType<?> type() {
        return HorticultureWorld.ORANGE_TREE_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@Nonnull IWorldGenerationReader world, @Nonnull Random random, @Nonnull BaseTreeFeatureConfig config,
                                 int trunkHeight, @Nonnull Foliage foliage, int foliageHeight, int radius, @Nonnull Set<BlockPos> leaves, int offset, @Nonnull MutableBoundingBox boundingBox) {
        int extraBranches = trunkHeight - 7;
        int width = Math.max(2, trunkHeight / 2) + 3;
        int branch = foliageHeight - (trunkHeight / 2);
        if (trunkHeight % 2 == 1) branch++;
        placeLeavesRow(world, random, config, foliage.foliagePos().above(5), width - 3, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().above(4), width - 1, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().above(3), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().above(2), width + 1, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().above(), width + 2, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos(), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().below(), width - 1, leaves, branch, foliage.doubleTrunk(), boundingBox);
        placeLeavesRow(world, random, config, foliage.foliagePos().below(2), width -2, leaves, branch, foliage.doubleTrunk(), boundingBox);

        for (int i = 0; i < extraBranches; i++) {
            if (i % 2 == 0) {
                placeLeavesRow(world, random, config, foliage.foliagePos().above((i + 1) * 2).above(), 1 + width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().above((i + 1) * 2), 2 + width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().above((i + 1) * 2).below(), width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().above((i + 1) * 2).below(2), width - (i + 1) - 2, leaves, branch, foliage.doubleTrunk(), boundingBox);
            } else {
                placeLeavesRow(world, random, config, foliage.foliagePos().below(i * 2).above(), 1 + width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().below(i * 2), 2 + width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().below(i * 2).below(), width - (i + 1), leaves, branch, foliage.doubleTrunk(), boundingBox);
                placeLeavesRow(world, random, config, foliage.foliagePos().below(i * 2).below(2), width - (i + 1) - 1, leaves, branch, foliage.doubleTrunk(), boundingBox);
            }
        }

        //placeLeavesRow(world, random, config, foliage.foliagePos().east(), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
        // placeLeavesRow(world, random, config, foliage.foliagePos().west(), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
        //placeLeavesRow(world, random, config, foliage.foliagePos().north(), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
        //placeLeavesRow(world, random, config, foliage.foliagePos().south(), width, leaves, branch, foliage.doubleTrunk(), boundingBox);
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
        return distance > ((4 * radius)) + 2 || (ew == ns && random.nextInt(3) == 0);
    }
}
