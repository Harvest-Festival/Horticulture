package uk.joshiejack.horticulture.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.Inverted;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.CropBlock;
import uk.joshiejack.horticulture.block.FruitBlock;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.item.HorticultureItems;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HorticultureLootTables extends LootTableProvider {
    public HorticultureLootTables(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
        map.forEach((name, table) -> LootTableManager.validate(validationtracker, name, table));
    }

    @Nonnull
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK), Pair.of(Chests::new, LootParameterSets.CHEST));
    }

    public static class Blocks extends BlockLootTables {
        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream()
                    .filter((block) -> Horticulture.MODID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
                    .collect(Collectors.toList());
        }

        private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
        private static final float[] JUNGLE_LEAVES_SAPLING_CHANGES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

        @Override
        protected void addTables() {

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
        }

        protected void addCrop(CropBlock block, Item item, Item seeds) {
            ILootCondition.IBuilder grown = BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(block.getAgeProperty(), block.getMaxAge()));
            add(block, applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(grown).add(ItemLootEntry.lootTableItem(item)))).withPool(LootPool.lootPool().when(Inverted.invert(grown)).add(ItemLootEntry.lootTableItem(seeds))));
        }

        protected void addFruit(Block block, Item item) {
            ILootCondition.IBuilder grown = BlockStateProperty.hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(FruitBlock.AGE, 3));
            add(block, applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().when(grown).add(ItemLootEntry.lootTableItem(item)))));
        }
    }

    public static class Chests extends ChestLootTables {
        private static ResourceLocation chest(ResourceLocation rl) {
            return new ResourceLocation(Horticulture.MODID, rl.getPath());
        }

        @Override
        public void accept(@Nonnull BiConsumer<ResourceLocation, LootTable.Builder> builder) {
            builder.accept(chest(LootTables.IGLOO_CHEST), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(2.0F, 8.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(12)
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F))))));
            builder.accept(chest(LootTables.VILLAGE_TAIGA_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 8.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F))))));
            builder.accept(chest(LootTables.VILLAGE_SNOWY_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 8.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.SWEET_POTATO_SEEDS.get()).setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 4.0F))))));
            builder.accept(chest(LootTables.VILLAGE_PLAINS_HOUSE), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 8.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.CORN_SEEDS.get()).setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.ORANGE_SAPLING.get()).setWeight(8)
                            .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.PEACH_SAPLING.get()).setWeight(5)
                            .apply(SetCount.setCount(RandomValueRange.between(1.0F, 3.0F))))));
            builder.accept(chest(LootTables.VILLAGE_WEAPONSMITH), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(3.0F, 8.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.CORN_SEEDS.get()).setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(1.0F, 2.0F))))));
            builder.accept(chest(LootTables.SIMPLE_DUNGEON), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(RandomValueRange.between(1.0F, 3.0F))
                    .add(ItemLootEntry.lootTableItem(HorticultureItems.STRAWBERRY_SEEDS.get()).setWeight(10)
                            .apply(SetCount.setCount(RandomValueRange.between(2.0F, 3.0F))))));
        }
    }
}
