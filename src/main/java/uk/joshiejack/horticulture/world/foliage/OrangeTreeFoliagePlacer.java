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

    }

    @Override
    public int foliageHeight(@Nonnull Random random, int trunkHeight, @Nonnull BaseTreeFeatureConfig config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(@Nonnull Random random, int baseHeight, int x, int y, int z, boolean giantTrunk) {
        return false;
    }
}
