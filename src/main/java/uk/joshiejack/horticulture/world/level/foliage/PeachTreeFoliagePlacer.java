//package uk.joshiejack.horticulture.world.level.foliage;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.util.valueproviders.IntProvider;
//import net.minecraft.world.gen.IWorldGenerationReader;
//import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
//import net.minecraft.world.gen.feature.FeatureSpread;
//import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
//import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
//import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
//import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
//import uk.joshiejack.horticulture.world.HorticultureLevel;
//
//import javax.annotation.Nonnull;
//import java.util.Random;
//import java.util.Set;
//
//public class PeachTreeFoliagePlacer extends FoliagePlacer {
//    public static final Codec<PeachTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, PeachTreeFoliagePlacer::new));
//
//    public PeachTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
//        super(radius, offset);
//    }
//
//    @Nonnull
//    @Override
//    protected FoliagePlacerType<?> type() {
//        return HorticultureLevel.PEACH_TREE_FOLIAGE_PLACER.get();
//    }
//
//    @Override
//    protected void createFoliage(@Nonnull IWorldGenerationReader world, @Nonnull Random random, @Nonnull BaseTreeFeatureConfig config,
//                                 int trunkHeight, @Nonnull Foliage foliage, int foliageHeight, int radius, @Nonnull Set<BlockPos> leaves, int offset, @Nonnull MutableBoundingBox boundingBox) {
//        int top = foliageHeight + 2;
//        int bottom = foliageHeight - (trunkHeight + 10);
//        if (trunkHeight %2 == 1) bottom++;
//        int size = Math.abs(top - bottom);
//        for (int r = 0; r <= size/2; r++) {
//            placeLeavesRow(world, random, config, foliage.foliagePos(), Math.min(r, size / 3 + 1), leaves, bottom + r, foliage.doubleTrunk(), boundingBox);
//            placeLeavesRow(world, random, config, foliage.foliagePos(), Math.min(r, size / 3 + 1), leaves, top - r, foliage.doubleTrunk(), boundingBox);
//        }
//    }
//
//    @Override
//    public int foliageHeight(@Nonnull Random random, int trunkHeight, @Nonnull BaseTreeFeatureConfig config) {
//        return 0;
//    }
//
//    @Override
//    protected boolean shouldSkipLocation(@Nonnull Random random, int ns, int y, int ew, int radius, boolean giantTrunk) {
//        BlockPos centre = BlockPos.ZERO;
//        BlockPos leaves = new BlockPos(ns, 0, ew);
//        int distance = centre.distManhattan(leaves);
//        return distance > radius || ew >=  radius - 1 || ns >=  radius - 1 || ((ew >= radius - 2 || ns >= radius - 2) && random.nextInt(3) == 1);
//    }
//}
