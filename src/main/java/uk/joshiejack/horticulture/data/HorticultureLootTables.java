package uk.joshiejack.horticulture.data;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.CropBlock;
import uk.joshiejack.horticulture.world.block.FruitBlock;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.item.HorticultureItems;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class HorticultureLootTables extends LootTableProvider {
    public HorticultureLootTables(PackOutput output) {
        super(output, Set.of(), List.of(new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(Chests::new, LootContextParamSets.CHEST)));
    }

    public static class Blocks extends BlockLootSubProvider {
        public Blocks() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return HorticultureBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::get).collect(Collectors.toList());
        }

        private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
        private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

        @Override
        protected void generate() {
            addCrop(HorticultureBlocks.TURNIPS.get(), HorticultureItems.TURNIP.get(), HorticultureItems.TURNIP_SEEDS.get());
            addCrop(HorticultureBlocks.CUCUMBERS.get(), HorticultureItems.CUCUMBER.get(), HorticultureItems.CUCUMBER_SEEDS.get());
            addCrop(HorticultureBlocks.STRAWBERRIES.get(), HorticultureItems.STRAWBERRY.get(), HorticultureItems.STRAWBERRY_SEEDS.get());
            addCrop(HorticultureBlocks.CABBAGES.get(), HorticultureItems.CABBAGE.get(), HorticultureItems.CABBAGE_SEEDS.get());
            addCrop(HorticultureBlocks.ONIONS.get(), HorticultureItems.ONION.get(), HorticultureItems.ONION_SEEDS.get());
            addCrop(HorticultureBlocks.TOMATOES.get(), HorticultureItems.TOMATO.get(), HorticultureItems.TOMATO_SEEDS.get());
            addCrop(HorticultureBlocks.CORN.get(), HorticultureItems.CORN.get(), HorticultureItems.CORN_SEEDS.get());
            addCrop(HorticultureBlocks.PINEAPPLES.get(), HorticultureItems.PINEAPPLE.get(), HorticultureItems.PINEAPPLE_SEEDS.get());
            addCrop(HorticultureBlocks.EGGPLANTS.get(), HorticultureItems.EGGPLANT.get(), HorticultureItems.EGGPLANT_SEEDS.get());
            addCrop(HorticultureBlocks.SPINACH.get(), HorticultureItems.SPINACH.get(), HorticultureItems.SPINACH_SEEDS.get());
            addCrop(HorticultureBlocks.SWEET_POTATOES.get(), HorticultureItems.SWEET_POTATO.get(), HorticultureItems.SWEET_POTATO_SEEDS.get());
            addCrop(HorticultureBlocks.GREEN_PEPPERS.get(), HorticultureItems.GREEN_PEPPER.get(), HorticultureItems.GREEN_PEPPER_SEEDS.get());
            dropSelf(HorticultureBlocks.APPLE_SAPLING.get());
            dropSelf(HorticultureBlocks.BANANA_SAPLING.get());
            dropSelf(HorticultureBlocks.ORANGE_SAPLING.get());
            dropSelf(HorticultureBlocks.PEACH_SAPLING.get());
            addFruit(HorticultureBlocks.APPLE_FRUIT.get(), Items.APPLE);
            addFruit(HorticultureBlocks.BANANA_FRUIT.get(), HorticultureItems.BANANA.get());
            addFruit(HorticultureBlocks.ORANGE_FRUIT.get(), HorticultureItems.ORANGE.get());
            addFruit(HorticultureBlocks.PEACH_FRUIT.get(), HorticultureItems.PEACH.get());
            add(HorticultureBlocks.APPLE_LEAVES.get(), (b) -> createLeavesDrops(b, HorticultureBlocks.APPLE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            add(HorticultureBlocks.ORANGE_LEAVES.get(), (b) -> createLeavesDrops(b, HorticultureBlocks.ORANGE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            add(HorticultureBlocks.PEACH_LEAVES.get(), (b) -> createLeavesDrops(b, HorticultureBlocks.PEACH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
            add(HorticultureBlocks.BANANA_LEAVES.get(), (b) -> createLeavesDrops(b, HorticultureBlocks.BANANA_SAPLING.get(), JUNGLE_LEAVES_SAPLING_CHANGES));
            dropSelf(HorticultureBlocks.SEED_MAKER.get());
            dropSelf(HorticultureBlocks.OLD_SPRINKLER.get());
            dropSelf(HorticultureBlocks.IRON_SPRINKLER.get());
            dropSelf(HorticultureBlocks.OAK_STUMP.get());
            dropSelf(HorticultureBlocks.SPRUCE_STUMP.get());
            dropSelf(HorticultureBlocks.BIRCH_STUMP.get());
            dropSelf(HorticultureBlocks.JUNGLE_STUMP.get());
            dropSelf(HorticultureBlocks.ACACIA_STUMP.get());
            dropSelf(HorticultureBlocks.DARK_OAK_STUMP.get());
            dropSelf(HorticultureBlocks.CRIMSON_STUMP.get());
            dropSelf(HorticultureBlocks.WARPED_STUMP.get());
            dropSelf(HorticultureBlocks.CHERRY_STUMP.get());
            dropSelf(HorticultureBlocks.MANGROVE_STUMP.get());
        }

        protected void addCrop(CropBlock block, Item item, Item seeds) {
            LootItemCondition.Builder grown = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(block.getAgeProperty(), block.getMaxAge()));
            add(block, applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(grown).add(LootItem.lootTableItem(item)))).withPool(LootPool.lootPool().when(grown.invert()).add(LootItem.lootTableItem(seeds))));
        }

        protected void addFruit(Block block, Item item) {
            LootItemCondition.Builder grown = LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FruitBlock.AGE, 3));
            add(block, applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(grown).add(LootItem.lootTableItem(item)))));
        }
    }

    public static class Chests implements LootTableSubProvider {
        private static ResourceLocation chest(ResourceLocation rl) {
            return new ResourceLocation(Horticulture.MODID, rl.getPath());
        }

        @Override
        public void generate(@Nonnull BiConsumer<ResourceLocation, LootTable.Builder> builder) {
            builder.accept(chest(BuiltInLootTables.IGLOO_CHEST), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(2.0F, 8.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(12)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))));
            builder.accept(chest(BuiltInLootTables.VILLAGE_TAIGA_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 8.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(10)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))));
            builder.accept(chest(BuiltInLootTables.VILLAGE_SNOWY_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 8.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(10)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))));
            builder.accept(chest(BuiltInLootTables.VILLAGE_PLAINS_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 8.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.CORN_SEEDS.get()).setWeight(10)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                    .add(LootItem.lootTableItem(HorticultureBlocks.ORANGE_SAPLING.get()).setWeight(8)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                    .add(LootItem.lootTableItem(HorticultureBlocks.PEACH_SAPLING.get()).setWeight(5)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))));
            builder.accept(chest(BuiltInLootTables.VILLAGE_WEAPONSMITH), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 8.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.CORN_SEEDS.get()).setWeight(10)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));
            builder.accept(chest(BuiltInLootTables.SIMPLE_DUNGEON), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 3.0F))
                    .add(LootItem.lootTableItem(HorticultureItems.STRAWBERRY_SEEDS.get()).setWeight(10)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))));
        }
    }
}
