package uk.joshiejack.horticulture.world.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.data.HorticultureDataProvider;
import uk.joshiejack.horticulture.world.item.HorticultureItems;

import java.util.Optional;
import java.util.function.Supplier;


@SuppressWarnings("unused")
public class HorticultureBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Horticulture.MODID);
    public static final TreeGrower APPLE_TREE = new TreeGrower("horticulture:apple", Optional.empty(), Optional.of(HorticultureDataProvider.APPLE_TREE.configured()), Optional.empty());
    public static final TreeGrower BANANA_TREE = new TreeGrower("horticulture:banana", Optional.empty(), Optional.of(HorticultureDataProvider.BANANA_TREE.configured()), Optional.empty());
    public static final TreeGrower ORANGE_TREE = new TreeGrower("horticulture:orange", Optional.empty(), Optional.of(HorticultureDataProvider.ORANGE_TREE.configured()), Optional.empty());
    public static final TreeGrower PEACH_TREE = new TreeGrower("horticulture:peach", Optional.empty(), Optional.of(HorticultureDataProvider.PEACH_TREE.configured()), Optional.empty());
    public static final DeferredHolder<Block, CropBlock> TURNIPS = BLOCKS.register("turnips", () -> new CropBlock(3, HorticultureItems.TURNIP_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>CUCUMBERS = BLOCKS.register("cucumbers", () -> new CropBlock(4, 2, HorticultureItems.CUCUMBER_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>STRAWBERRIES = BLOCKS.register("strawberries", () -> new CropBlock(4, 2, HorticultureItems.STRAWBERRY_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>CABBAGES = BLOCKS.register("cabbages", () -> new CropBlock(4, HorticultureItems.CABBAGE_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>ONIONS = BLOCKS.register("onions", () -> new CropBlock(3, HorticultureItems.ONION_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>TOMATOES = BLOCKS.register("tomatoes", () -> new CropBlock(5, 2, HorticultureItems.TOMATO_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>CORN = BLOCKS.register("corn", () -> new CropBlock(5, 2, HorticultureItems.CORN_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>PINEAPPLES = BLOCKS.register("pineapples", () -> new CropBlock(5, 1, HorticultureItems.PINEAPPLE_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>EGGPLANTS = BLOCKS.register("eggplants", () -> new CropBlock(4, 1, HorticultureItems.EGGPLANT_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>SPINACH = BLOCKS.register("spinach", () -> new CropBlock(3, HorticultureItems.SPINACH_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>SWEET_POTATOES = BLOCKS.register("sweet_potatoes", () -> new CropBlock(3, 0, HorticultureItems.SWEET_POTATO_SEEDS::get));
    public static final DeferredHolder<Block, CropBlock>GREEN_PEPPERS = BLOCKS.register("green_peppers", () -> new CropBlock(5, 3, HorticultureItems.GREEN_PEPPER_SEEDS::get));
    public static final DeferredBlock<Block> APPLE_SAPLING = registerWithItem("apple_sapling", () -> new SaplingBlock(APPLE_TREE, Block.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> BANANA_SAPLING = registerWithItem("banana_sapling", () -> new SaplingBlock(BANANA_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SAPLING)));
    public static final DeferredBlock<Block> ORANGE_SAPLING = registerWithItem("orange_sapling", () -> new SaplingBlock(ORANGE_TREE, Block.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> PEACH_SAPLING = registerWithItem("peach_sapling", () -> new SaplingBlock(PEACH_TREE, Block.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<Block> BANANA_FRUIT = registerWithItem("banana_fruit", () -> new FruitBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> ORANGE_FRUIT = registerWithItem("orange_fruit",  () -> new FruitBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> APPLE_FRUIT = registerWithItem("apple_fruit",  () -> new FruitBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> PEACH_FRUIT = registerWithItem("peach_fruit",  () -> new FruitBlock(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<Block> BANANA_LEAVES = registerWithItem("banana_leaves", () -> new TropicalFruitTreeLeavesBlock(BANANA_FRUIT));
    public static final DeferredBlock<Block> ORANGE_LEAVES = registerWithItem("orange_leaves", () -> new TemperateFruitTreeLeavesBlock(ORANGE_FRUIT));
    public static final DeferredBlock<Block> APPLE_LEAVES = registerWithItem("apple_leaves", () -> new TemperateFruitTreeLeavesBlock(APPLE_FRUIT));
    public static final DeferredBlock<Block> PEACH_LEAVES = registerWithItem("peach_leaves", () -> new TemperateFruitTreeLeavesBlock(PEACH_FRUIT));
    public static final DeferredBlock<Block> SEED_MAKER = registerWithItem("seed_maker", SeedMakerBlock::new);
    public static final DeferredBlock<Block> OLD_SPRINKLER = registerWithItem("old_sprinkler", OldSprinklerBlock::new);
    public static final DeferredBlock<Block> IRON_SPRINKLER = registerWithItem("iron_sprinkler", IronSprinklerBlock::new);
    public static final DeferredBlock<Block> OAK_STUMP = registerWithItem("oak_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredBlock<Block> SPRUCE_STUMP = registerWithItem("spruce_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    public static final DeferredBlock<Block> BIRCH_STUMP = registerWithItem("birch_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.BIRCH_PLANKS)));
    public static final DeferredBlock<Block> JUNGLE_STUMP = registerWithItem("jungle_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)));
    public static final DeferredBlock<Block> ACACIA_STUMP = registerWithItem("acacia_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS)));
    public static final DeferredBlock<Block> DARK_OAK_STUMP = registerWithItem("dark_oak_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)));
    public static final DeferredBlock<Block> CHERRY_STUMP = registerWithItem("cherry_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)));
    public static final DeferredBlock<Block> MANGROVE_STUMP = registerWithItem("mangrove_stump", () -> new StumpBlock(Block.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)));
    public static final DeferredBlock<Block> CRIMSON_STUMP = registerWithItem("crimson_stump", () -> new NetherStumpBlock(Block.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)));
    public static final DeferredBlock<Block> WARPED_STUMP = registerWithItem("warped_stump", () -> new NetherStumpBlock(Block.Properties.ofFullCopy(Blocks.WARPED_PLANKS)));

    private static DeferredBlock<Block> registerWithItem(String name, Supplier<Block> block) {
        DeferredBlock<Block> blockDeferred = BLOCKS.register(name, block);
        HorticultureItems.ITEMS.register(name, () -> new BlockItem(blockDeferred.get(), new Item.Properties()));
        return blockDeferred;
    }




}
