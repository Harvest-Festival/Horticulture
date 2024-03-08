package uk.joshiejack.horticulture.world;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.level.foliage.NewAppleTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewBananaTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewOrangeTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.foliage.NewPeachTreeFoliagePlacer;
import uk.joshiejack.horticulture.world.level.trunk.AppleTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.BananaTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.OrangeTreeTrunkPlacer;
import uk.joshiejack.horticulture.world.level.trunk.PeachTreeTrunkPlacer;

public class HorticultureLevel {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Horticulture.MODID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Horticulture.MODID);
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<NewAppleTreeFoliagePlacer>> APPLE_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("apple_tree", () -> new FoliagePlacerType<>(NewAppleTreeFoliagePlacer.CODEC));
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<NewOrangeTreeFoliagePlacer>> ORANGE_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("orange_tree", () -> new FoliagePlacerType<>(NewOrangeTreeFoliagePlacer.CODEC));
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<NewPeachTreeFoliagePlacer>> PEACH_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("peach_tree", () -> new FoliagePlacerType<>(NewPeachTreeFoliagePlacer.CODEC));
    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<NewBananaTreeFoliagePlacer>> BANANA_TREE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("banana_tree", () -> new FoliagePlacerType<>(NewBananaTreeFoliagePlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<AppleTreeTrunkPlacer>> APPLE_TREE_TRUNK_PLACER = TRUNK_PLACERS.register("apple_tree", () -> new TrunkPlacerType<>(AppleTreeTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<OrangeTreeTrunkPlacer>> ORANGE_TREE_TRUNK_PLACER = TRUNK_PLACERS.register("orange_tree", () -> new TrunkPlacerType<>(OrangeTreeTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<PeachTreeTrunkPlacer>> PEACH_TREE_TRUNK_PLACER = TRUNK_PLACERS.register("peach_tree", () -> new TrunkPlacerType<>(PeachTreeTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BananaTreeTrunkPlacer>> BANANA_TREE_TRUNK_PLACER = TRUNK_PLACERS.register("banana_tree", () -> new TrunkPlacerType<>(BananaTreeTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
        TRUNK_PLACERS.register(eventBus);
    }
}