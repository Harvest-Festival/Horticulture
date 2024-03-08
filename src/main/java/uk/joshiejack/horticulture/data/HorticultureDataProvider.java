package uk.joshiejack.horticulture.data;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.level.foliage.NewAppleTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewBananaTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewOrangeTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewPeachTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.trunk.AppleTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.BananaTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.OrangeTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.PeachTreeTrunkPlacer;
import uk.joshiejack.penguinlib.data.generator.DatapackUtils;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class HorticultureDataProvider extends DatapackBuiltinEntriesProvider {
    public static final DatapackUtils.Feature APPLE_TREE = DatapackUtils.createKey(new ResourceLocation(Horticulture.MODID, "apple_tree"));
    public static final DatapackUtils.Feature BANANA_TREE = DatapackUtils.createKey(new ResourceLocation(Horticulture.MODID, "banana_tree"));
    public static final DatapackUtils.Feature ORANGE_TREE = DatapackUtils.createKey(new ResourceLocation(Horticulture.MODID, "orange_tree"));
    public static final DatapackUtils.Feature PEACH_TREE = DatapackUtils.createKey(new ResourceLocation(Horticulture.MODID, "peach_tree"));
    public static final ResourceKey<BiomeModifier> APPLE_TREE_PLACER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Horticulture.MODID, "apple_tree"));
    public static final ResourceKey<BiomeModifier> BANANA_TREE_PLACER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Horticulture.MODID, "banana_tree"));


    public HorticultureDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, (ctx) -> HorticultureDataProvider.bootstrap(null, ctx))
                        .add(Registries.PLACED_FEATURE, (ctx) -> HorticultureDataProvider.bootstrap(ctx, null))
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, HorticultureDataProvider::bootstrapModifiers)
                , Set.of("minecraft", Horticulture.MODID));
    }

    private static void bootstrapModifiers(BootstapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> features = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderSet<Biome> forests = biomes.getOrThrow(BiomeTags.IS_FOREST);
        HolderSet<Biome> jungles = biomes.getOrThrow(BiomeTags.IS_JUNGLE);
        HolderSet<PlacedFeature> appleTree = HolderSet.direct(features.getOrThrow(APPLE_TREE.feature()));
        HolderSet<PlacedFeature> bananaTree = HolderSet.direct(features.getOrThrow(BANANA_TREE.feature()));
        context.register(APPLE_TREE_PLACER, new BiomeModifiers.AddFeaturesBiomeModifier(forests, appleTree, GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(BANANA_TREE_PLACER, new BiomeModifiers.AddFeaturesBiomeModifier(jungles, bananaTree, GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    public static void bootstrap(BootstapContext<PlacedFeature> ftrContext, BootstapContext<ConfiguredFeature<?, ?>> cnfContext) {
        DatapackUtils.registerFeatures(ftrContext, cnfContext, APPLE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG), new AppleTreeTrunkPlacer(4, 4, 0),
                BlockStateProvider.simple(HorticultureBlocks.APPLE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 5)), new NewAppleTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build(),
                CountPlacement.of(16), InSquarePlacement.spread(), BiomeFilter.biome());

        DatapackUtils.registerFeatures(ftrContext, cnfContext, BANANA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.JUNGLE_LOG), new BananaTreeTrunkPlacer(5, 5, 0),
                BlockStateProvider.simple(HorticultureBlocks.BANANA_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 3)), new NewBananaTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build(),
                CountPlacement.of(4), InSquarePlacement.spread(), BiomeFilter.biome());

        DatapackUtils.registerFeatures(ftrContext, cnfContext, ORANGE_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG), new OrangeTreeTrunkPlacer(5, 4, 0),
                BlockStateProvider.simple(HorticultureBlocks.ORANGE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 4)), new NewOrangeTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        DatapackUtils.registerFeatures(ftrContext, cnfContext, PEACH_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG), new PeachTreeTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(HorticultureBlocks.PEACH_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 3)), new NewPeachTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    }
}
