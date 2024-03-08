//package uk.joshiejack.horticulture.world.level.foliage;
//
//import com.mojang.serialization.Codec;
//import com.mojang.serialization.codecs.RecordCodecBuilder;
//import net.minecraft.util.Direction;
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
//public class BananaTreeFoliagePlacer extends FoliagePlacer {
//    public static final Codec<BananaTreeFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance).apply(instance, BananaTreeFoliagePlacer::new));
//
//    public BananaTreeFoliagePlacer(IntProvider radius, IntProvider offset) {
//        super(radius, offset);
//    }
//
//    @Nonnull
//    @Override
//    protected FoliagePlacerType<?> type() {
//        return HorticultureLevel.BANANA_TREE_FOLIAGE_PLACER.get();
//    }
//
//    @Override
//    protected void createFoliage(@Nonnull IWorldGenerationReader world, @Nonnull Random random, @Nonnull BaseTreeFeatureConfig config,
//                                 int trunkHeight, @Nonnull Foliage foliage, int foliageHeight, int radius, @Nonnull Set<BlockPos> leaves, int offset, @Nonnull MutableBoundingBox boundingBox) {
//        placeLeavesRow(world, random, config, foliage.foliagePos().above(), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//        int count = 0;
//        for (Direction dir: Direction.Plane.HORIZONTAL) {
//            placeLeavesRow(world, random, config, foliage.foliagePos().below().relative(dir, 2), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//            placeLeavesRow(world, random, config, foliage.foliagePos().below(2).relative(dir, 3), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//            if (random.nextBoolean()) {
//                placeLeavesRow(world, random, config, foliage.foliagePos().below().relative(dir, 2).relative(dir.getCounterClockWise(), 2), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//                placeLeavesRow(world, random, config, foliage.foliagePos().below(2).relative(dir, 3).relative(dir.getCounterClockWise(), 2), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//            }
//
//            if (random.nextBoolean() && count < 5) {
//                placeLeavesRow(world, random, config, foliage.foliagePos().above().relative(dir), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//                count++;
//                for (Direction direction: Direction.Plane.HORIZONTAL) {
//                    if (random.nextBoolean()) {
//                        placeLeavesRow(world, random, config, foliage.foliagePos().above().relative(dir).relative(direction), 0, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//                        count++;
//
//                        if (count > 5)
//                            break;
//                    }
//                }
//            }
//        }
//
//        placeLeavesRow(world, random, config, foliage.foliagePos(), 1, leaves, foliageHeight, foliage.doubleTrunk(), boundingBox);
//    }
//
//    @Override
//    public int foliageHeight(@Nonnull Random random, int trunkHeight, @Nonnull BaseTreeFeatureConfig config) {
//        return 0;
//    }
//
//    @Override
//    protected boolean shouldSkipLocation(@Nonnull Random random, int baseHeight, int x, int y, int z, boolean giantTrunk) {
//        return false;
//    }
//}
