package uk.joshiejack.horticulture.data;

import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.FruitBlock;
import uk.joshiejack.horticulture.block.FruitTreeLeavesBlock;
import uk.joshiejack.horticulture.block.HorticultureBlocks;

public class HorticultureBlockStates extends BlockStateProvider {
    public HorticultureBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Horticulture.MODID, exFileHelper);
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
        //buildCrops(HorticultureBlocks.GRAPES.get());
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
        model(HorticultureBlocks.SEED_MAKER.get());
        mushroomLogs(HorticultureBlocks.OAK_STUMP.get());
        mushroomLogs(HorticultureBlocks.SPRUCE_STUMP.get());
        mushroomLogs(HorticultureBlocks.BIRCH_STUMP.get());
        mushroomLogs(HorticultureBlocks.JUNGLE_STUMP.get());
        mushroomLogs(HorticultureBlocks.ACACIA_STUMP.get());
        mushroomLogs(HorticultureBlocks.DARK_OAK_STUMP.get());
        mushroomLogs(HorticultureBlocks.CRIMSON_STUMP.get());
        mushroomLogs(HorticultureBlocks.WARPED_STUMP.get());
        leaves(HorticultureBlocks.PEACH_LEAVES.get());
        leaves(HorticultureBlocks.ORANGE_LEAVES.get());
        leaves(HorticultureBlocks.APPLE_LEAVES.get());
        leaves(HorticultureBlocks.BANANA_LEAVES.get());
        //models().createTrivialBlock(Blocks.BIRCH_LEAVES, TexturedModel.LEAVES);
    }

    protected void leaves(Block block) {
        getMultipartBuilder(block)
                .part().modelFile(itemModels().getExistingFile(new ResourceLocation("minecraft", "oak_leaves"))).addModel().end()
                .part().modelFile(models()
                .cubeAll(block.getRegistryName().getPath(), new ResourceLocation(block.getRegistryName().getNamespace(),
                        "block/leaves/" + block.getRegistryName().getPath().replace("_leaves", "")))).addModel().condition(FruitTreeLeavesBlock.IN_SEASON, true);
        /*
        getVariantBuilder(block);
        ConfiguredModel.builder().modelFile(models()
                .cubeAll(block.getRegistryName().getPath(), new ResourceLocation(block.getRegistryName().getNamespace(),
                        "block/leaves/" + block.getRegistryName().getPath().replace("_leaves", "")))).build();*/
    }

    protected void mushroomLogs(Block block) {
        String log = block.getRegistryName().getPath().replace("_stump", "");
        String type = log.equals("crimson") || log.equals("warped") ? "_stem" : "_log";
        ModelFile file = models().withExistingParent(block.getRegistryName().getPath(), new ResourceLocation(Horticulture.MODID, "mushroom_log"))
                .texture("side", new ResourceLocation("minecraft", "block/" + log + type)).texture("top", new ResourceLocation("block/" + log + type + "_top"));
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void model(Block block) {
        ModelFile file = models().getExistingFile(block.getRegistryName());
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void buildSaplings(Block block) {
        ModelFile file = models().cross(block.getRegistryName().toString(),
                new ResourceLocation(block.getRegistryName().getNamespace(), "block/saplings/" + block.getRegistryName().getPath().replace("_sapling", "")));
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.builder().modelFile(file).build());
    }

    protected void buildCrops(CropsBlock block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (int i = 0; i <= block.getMaxAge(); i++) {
            ModelFile file = models().crop(block.getRegistryName().getPath() + "_" + (i + 1),
                    new ResourceLocation(Horticulture.MODID, "block/crops/" + block.getRegistryName().getPath() + "_" + (i + 1)));
            builder.partialState().with(block.getAgeProperty(), i).modelForState().modelFile(file).addModel();
        }
    }

    protected void buildFruits(Block block) {
        VariantBlockStateBuilder builder = getVariantBuilder(block);
        for (int i = 0; i <= 3; i++) {
            ModelFile file = models().cross(block.getRegistryName().getPath() + "_" + (i + 1),
                    new ResourceLocation(Horticulture.MODID, "block/crops/" + block.getRegistryName().getPath() + "_" + i));
            builder.partialState().with(FruitBlock.AGE, i).modelForState().modelFile(file).addModel();
        }
    }
}
