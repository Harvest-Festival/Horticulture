package uk.joshiejack.horticulture.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.trees.AppleTree;
import uk.joshiejack.horticulture.block.trees.BananaTree;
import uk.joshiejack.horticulture.block.trees.OrangeTree;
import uk.joshiejack.horticulture.block.trees.PeachTree;
import uk.joshiejack.horticulture.tileentity.NetherStumpTileEntity;
import uk.joshiejack.horticulture.tileentity.StumpTileEntity;


@SuppressWarnings("unused")
public class HorticultureBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Horticulture.MODID);

    public static final RegistryObject<CropBlock> TURNIPS = BLOCKS.register("turnips", () -> new CropBlock(3));
    public static final RegistryObject<CropBlock> CUCUMBERS = BLOCKS.register("cucumbers", () -> new CropBlock(4, 2));
    public static final RegistryObject<CropBlock> STRAWBERRIES = BLOCKS.register("strawberries", () -> new CropBlock(4, 2));
    public static final RegistryObject<CropBlock> CABBAGES = BLOCKS.register("cabbages", () -> new CropBlock(4));
    public static final RegistryObject<CropBlock> ONIONS = BLOCKS.register("onions", () -> new CropBlock(3));
    public static final RegistryObject<CropBlock> TOMATOES = BLOCKS.register("tomatoes", () -> new CropBlock(5, 2));
    public static final RegistryObject<CropBlock> CORN = BLOCKS.register("corn", () -> new CropBlock(5, 2));
    public static final RegistryObject<CropBlock> PINEAPPLES = BLOCKS.register("pineapples", () -> new CropBlock(5, 1));
    public static final RegistryObject<CropBlock> EGGPLANTS = BLOCKS.register("eggplants", () -> new CropBlock(4, 1));
    public static final RegistryObject<CropBlock> SPINACH = BLOCKS.register("spinach", () -> new CropBlock(3));
    public static final RegistryObject<CropBlock> SWEET_POTATOES = BLOCKS.register("sweet_potatoes", () -> new CropBlock(3, 0));
    public static final RegistryObject<CropBlock> GREEN_PEPPERS = BLOCKS.register("green_peppers", () -> new CropBlock(5, 3));
    public static final RegistryObject<Block> APPLE_SAPLING = BLOCKS.register("apple_sapling", () -> new SaplingBlock(new AppleTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> BANANA_SAPLING = BLOCKS.register("banana_sapling", () -> new SaplingBlock(new BananaTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> ORANGE_SAPLING = BLOCKS.register("orange_sapling", () -> new SaplingBlock(new OrangeTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PEACH_SAPLING = BLOCKS.register("peach_sapling", () -> new SaplingBlock(new PeachTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final RegistryObject<Block> BANANA_FRUIT = BLOCKS.register("banana_fruit", FruitBlock::new);
    public static final RegistryObject<Block> ORANGE_FRUIT = BLOCKS.register("orange_fruit", FruitBlock::new);
    public static final RegistryObject<Block> APPLE_FRUIT = BLOCKS.register("apple_fruit", FruitBlock::new);
    public static final RegistryObject<Block> PEACH_FRUIT = BLOCKS.register("peach_fruit", FruitBlock::new);
    public static final RegistryObject<Block> BANANA_LEAVES = BLOCKS.register("banana_leaves", () -> new FruitTreeLeavesBlock(BANANA_FRUIT, HorticultureBlocks::canPlaceTropicalFruit));
    public static final RegistryObject<Block> ORANGE_LEAVES = BLOCKS.register("orange_leaves", () -> new FruitTreeLeavesBlock(ORANGE_FRUIT, HorticultureBlocks::canPlaceTemperateFruit));
    public static final RegistryObject<Block> APPLE_LEAVES = BLOCKS.register("apple_leaves", () -> new FruitTreeLeavesBlock(APPLE_FRUIT, HorticultureBlocks::canPlaceTemperateFruit));
    public static final RegistryObject<Block> PEACH_LEAVES = BLOCKS.register("peach_leaves", () -> new FruitTreeLeavesBlock(PEACH_FRUIT, HorticultureBlocks::canPlaceTemperateFruit));
    public static final RegistryObject<Block> SEED_MAKER = BLOCKS.register("seed_maker", SeedMakerBlock::new);
    public static final RegistryObject<Block> OLD_SPRINKLER = BLOCKS.register("old_sprinkler", OldSprinklerBlock::new);
    public static final RegistryObject<Block> IRON_SPRINKLER = BLOCKS.register("iron_sprinkler", IronSprinklerBlock::new);
    public static final RegistryObject<Block> OAK_STUMP = BLOCKS.register("oak_stump", () -> new StumpBlock(Blocks.OAK_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> SPRUCE_STUMP = BLOCKS.register("spruce_stump", () -> new StumpBlock(Blocks.SPRUCE_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> BIRCH_STUMP = BLOCKS.register("birch_stump", () -> new StumpBlock(Blocks.BIRCH_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> JUNGLE_STUMP = BLOCKS.register("jungle_stump", () -> new StumpBlock(Blocks.JUNGLE_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> ACACIA_STUMP = BLOCKS.register("acacia_stump", () -> new StumpBlock(Blocks.ACACIA_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> DARK_OAK_STUMP = BLOCKS.register("dark_oak_stump", () -> new StumpBlock(Blocks.DARK_OAK_LOG, StumpTileEntity::new));
    public static final RegistryObject<Block> CRIMSON_STUMP = BLOCKS.register("crimson_stump", () -> new StumpBlock(Blocks.CRIMSON_STEM, NetherStumpTileEntity::new));
    public static final RegistryObject<Block> WARPED_STUMP = BLOCKS.register("warped_stump", () -> new StumpBlock(Blocks.WARPED_STEM, NetherStumpTileEntity::new));

    private static Boolean canPlaceTemperateFruit(BlockState state, IBlockReader reader, BlockPos pos, RegistryObject<Block> block) {
        return BlockPos.betweenClosedStream(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))
                .noneMatch(target -> reader.getBlockState(target).getBlock() == block.get());
    }

    private static Boolean canPlaceTropicalFruit(BlockState state, IBlockReader reader, BlockPos pos, RegistryObject<Block> block) {
        for (Direction facing: Direction.Plane.HORIZONTAL) {
            if (BlockTags.LOGS.contains(reader.getBlockState(pos.relative(facing)).getBlock())) return true;
        }

        return false;
    }
}
