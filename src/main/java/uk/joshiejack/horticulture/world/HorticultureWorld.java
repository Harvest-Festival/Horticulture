package uk.joshiejack.horticulture.world;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.foliage.AppleTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.foliage.BananaTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.foliage.OrangeTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.foliage.PeachTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.trunk.AppleTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.trunk.BananaTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.trunk.OrangeTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.trunk.PeachTreeTrunkPlacer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Horticulture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorticultureWorld {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, Horticulture.MODID);
    public static final RegistryObject<FoliagePlacerType<?>> APPLE_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("apple_tree", () -> new FoliagePlacerType<>(AppleTreeFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> ORANGE_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("orange_tree", () -> new FoliagePlacerType<>(OrangeTreeFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> PEACH_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("peach_tree", () -> new FoliagePlacerType<>(PeachTreeFoliagePlacer.CODEC));
    public static final RegistryObject<FoliagePlacerType<?>> BANANA_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("banana_tree", () -> new FoliagePlacerType<>(BananaTreeFoliagePlacer.CODEC));
    public static TrunkPlacerType<?> APPLE_TREE_TRUNK_PLACER = null;
    public static TrunkPlacerType<?> ORANGE_TREE_TRUNK_PLACER = null;
    public static TrunkPlacerType<?> PEACH_TREE_TRUNK_PLACER = null;
    public static TrunkPlacerType<?> BANANA_TREE_TRUNK_PLACER = null;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> APPLE_TREE = null;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> ORANGE_TREE = null;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> PEACH_TREE = null;
    public static ConfiguredFeature<BaseTreeFeatureConfig, ?> BANANA_TREE = null;

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Horticulture.MODID, key), feature);
    }

    @SubscribeEvent
    public static void onFoliageLoader(RegistryEvent<FoliagePlacerType<?>> event) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        //TODO, Surely I need this to be thread safe???
        APPLE_TREE_TRUNK_PLACER = registerTrunkType("apple_tree", AppleTreeTrunkPlacer.CODEC);
        ORANGE_TREE_TRUNK_PLACER = registerTrunkType("orange_tree", OrangeTreeTrunkPlacer.CODEC);
        PEACH_TREE_TRUNK_PLACER = registerTrunkType("peach_tree", PeachTreeTrunkPlacer.CODEC);
        BANANA_TREE_TRUNK_PLACER = registerTrunkType("banana_tree", BananaTreeTrunkPlacer.CODEC);
    }

    @SuppressWarnings("rawtypes")
    private static <P extends AbstractTrunkPlacer> TrunkPlacerType<?> registerTrunkType(String name, Codec<P> codec) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<TrunkPlacerType> constructor = ObfuscationReflectionHelper.findConstructor(TrunkPlacerType.class, Codec.class);
        return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(Horticulture.MODID, name), constructor.newInstance(codec));
    }

    @SuppressWarnings("unchecked")
    private static ConfiguredFeature<BaseTreeFeatureConfig, ?> withChance(int chance,  ConfiguredFeature<?, ?> feature) {
        return (ConfiguredFeature<BaseTreeFeatureConfig, ?>) feature.chance(chance);
    }

    @SubscribeEvent
    public static void onSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            APPLE_TREE = register("apple_tree", withChance(8, Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(HorticultureBlocks.APPLE_LEAVES.get().defaultBlockState()), new AppleTreeFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), new AppleTreeTrunkPlacer(3, 5, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build())));
            ORANGE_TREE = register("orange_tree", withChance(6, Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(HorticultureBlocks.ORANGE_LEAVES.get().defaultBlockState()), new OrangeTreeFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), new OrangeTreeTrunkPlacer(4, 5, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build())));
            PEACH_TREE = register("peach_tree", withChance(4, Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), new SimpleBlockStateProvider(HorticultureBlocks.PEACH_LEAVES.get().defaultBlockState()), new PeachTreeFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), new PeachTreeTrunkPlacer(5, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build())));
            BANANA_TREE = register("banana_tree", withChance(4, Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.defaultBlockState()), new SimpleBlockStateProvider(HorticultureBlocks.BANANA_LEAVES.get().defaultBlockState()), new BananaTreeFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0)), new BananaTreeTrunkPlacer(4, 5, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build())));
        });
    }

    @Mod.EventBusSubscriber(modid = Horticulture.MODID)
    public static class Loader {
        @SubscribeEvent
        public static void onBiomeLoad(BiomeLoadingEvent event) {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(RegistryKey.create(Registry.BIOME_REGISTRY, Objects.requireNonNull(event.getName())));
            if (types.contains(BiomeDictionary.Type.JUNGLE))
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HorticultureWorld.BANANA_TREE);
            else if (types.contains(BiomeDictionary.Type.FOREST))
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HorticultureWorld.APPLE_TREE);
        }
    }
}
