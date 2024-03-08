package uk.joshiejack.horticulture.data;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.VariantBlockStateBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.*;

@SuppressWarnings("ConstantConditions")
public class HorticultureBlockStates extends BlockStateProvider {
    public HorticultureBlockStates(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        buildCrops(HorticultureBlocks.TURNIPS.get());
        buildCrops(HorticultureBlocks.CUCUMBERS.get());
        buildCrops(HorticultureBlocks.STRAWBERRIES.get());
        buildCrops(HorticultureBlocks.CABBAGES.get());
        buildCrops(HorticultureBlocks.ONIONS.get());
        buildCrops(HorticultureBlocks.TOMATOES.get());
        buildCrops(HorticultureBlocks.CORN.get());
        buildCrops(HorticultureBlocks.PINEAPPLES.get());
        buildCrops(HorticultureBlocks.EGGPLANTS.get());
        buildCrops(HorticultureBlocks.SPINACH.get());
        buildCrops(HorticultureBlocks.SWEET_POTATOES.get());
        buildCrops(HorticultureBlocks.GREEN_PEPPERS.get());
        buildFruits(HorticultureBlocks.APPLE_FRUIT.get());
        buildFruits(HorticultureBlocks.BANANA_FRUIT.get());
        buildFruits(HorticultureBlocks.ORANGE_FRUIT.get());
        buildFruits(HorticultureBlocks.PEACH_FRUIT.get());
        buildSaplings(HorticultureBlocks.APPLE_SAPLING.get());
        buildSaplings(HorticultureBlocks.BANANA_SAPLING.get());
        buildSaplings(HorticultureBlocks.PEACH_SAPLING.get());
        buildSaplings(HorticultureBlocks.ORANGE_SAPLING.get());
        model(HorticultureBlocks.OLD_SPRINKLER.get());
        model(HorticultureBlocks.IRON_SPRINKLER.get());
        mushroomLogs(HorticultureBlocks.OAK_STUMP.get());
        mushroomLogs(HorticultureBlocks.SPRUCE_STUMP.get());
        mushroomLogs(HorticultureBlocks.BIRCH_STUMP.get());
        mushroomLogs(HorticultureBlocks.JUNGLE_STUMP.get());
        mushroomLogs(HorticultureBlocks.ACACIA_STUMP.get());
        mushroomLogs(HorticultureBlocks.DARK_OAK_STUMP.get());
        mushroomLogs(HorticultureBlocks.CRIMSON_STUMP.get());
        mushroomLogs(HorticultureBlocks.WARPED_STUMP.get());
        mushroomLogs(HorticultureBlocks.CHERRY_STUMP.get());
        mushroomLogs(HorticultureBlocks.MANGROVE_STUMP.get());
        leaves(HorticultureBlocks.PEACH_LEAVES.get());
        leaves(HorticultureBlocks.ORANGE_LEAVES.get());
        leaves(HorticultureBlocks.APPLE_LEAVES.get());
        leaves(HorticultureBlocks.BANANA_LEAVES.get());
        ModelFile file = models().getExistingFile(HorticultureBlocks.SEED_MAKER.getId());
        VariantBlockStateBuilder builder = getVariantBuilder(HorticultureBlocks.SEED_MAKER.get());
        builder.partialState().with(SeedMakerBlock.FACING, Direction.EAST).modelForState().modelFile(file).rotationY(90).addModel();
        builder.partialState().with(SeedMakerBlock.FACING, Direction.WEST).modelForState().modelFile(file).rotationY(270).addModel();
        builder.partialState().with(SeedMakerBlock.FACING, Direction.NORTH).modelForState().modelFile(file).rotationY(0).addModel();
        builder.partialState().with(SeedMakerBlock.FACING, Direction.SOUTH).modelForState().modelFile(file).rotationY(180).addModel();
    }

    protected void leaves(Block block) {
        getMultipartBuilder(block)
                .part().modelFile(models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), new ResourceLocation("minecraft", "oak_leaves")).renderType("cutout_mipped")).addModel().end()
                .part().modelFile(models()
                .cubeAll(BuiltInRegistries.BLOCK.getKey(block).getPath() + "_in_season", new ResourceLocation(BuiltInRegistries.BLOCK.getKey(block).getNamespace(),
                        "block/leaves/" + BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_leaves", "")))).addModel().condition(AbstractFruitTreeLeavesBlock.IN_SEASON, true);
    }

    protected void mushroomLogs(Block block) {
        String log = BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_stump", "");
        String type = log.equals("crimson") || log.equals("warped") ? "_stem" : "_log";
        ModelFile file = models().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(), new ResourceLocation(Horticulture.MODID, "mushroom_log"))
                .texture("side", new ResourceLocation("minecraft", "block/" + log + type)).texture("top", new ResourceLocation("block/" + log + type + "_top"));
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void model(Block block) {
        ModelFile file = models().getExistingFile(BuiltInRegistries.BLOCK.getKey(block));
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void buildSaplings(Block block) {
        ModelFile file = models().cross(BuiltInRegistries.BLOCK.getKey(block).toString(),
                new ResourceLocation(BuiltInRegistries.BLOCK.getKey(block).getNamespace(), "block/saplings/" + BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_sapling", "")));
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void buildCrops(CropBlock block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (int i = 0; i <= block.getMaxAge(); i++) {
            ModelFile file = models().crop(BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + (i + 1),
                    new ResourceLocation(Horticulture.MODID, "block/crops/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + (i + 1)));
            builder.partialState().with(block.getAgeProperty(), i).modelForState().modelFile(file).addModel();
        }
    }

    protected void buildFruits(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (int i = 0; i <= 3; i++) {
            ModelFile file = models().cross(BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + (i + 1),
                    new ResourceLocation(Horticulture.MODID, "block/crops/" + BuiltInRegistries.BLOCK.getKey(block).getPath() + "_" + i));
            builder.partialState().with(FruitBlock.AGE, i).modelForState().modelFile(file).addModel();
        }
    }
}
