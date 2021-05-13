package uk.joshiejack.horticulture.block.trees;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;

import javax.annotation.Nullable;
import java.util.Random;

public class BananaTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random random, boolean flowersNearby) {
        if (random.nextInt(10) == 0) {
            return flowersNearby ? Features.FANCY_OAK_BEES_005 : Features.FANCY_OAK;
        } else {
            return flowersNearby ? Features.OAK_BEES_005 : Features.OAK;
        }
    }
}