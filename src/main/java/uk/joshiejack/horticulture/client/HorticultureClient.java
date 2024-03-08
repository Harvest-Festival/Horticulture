package uk.joshiejack.horticulture.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.client.renderer.FruitLeavesBakedModel;
import uk.joshiejack.horticulture.client.renderer.SeedMakerBlockEntityRenderer;
import uk.joshiejack.horticulture.client.renderer.StumpBakedModel;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Horticulture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorticultureClient {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(HorticultureBlockEntities.SEED_MAKER.get(), SeedMakerBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerAdditional(ModelEvent.RegisterAdditional event) {
        event.register(StumpBakedModel.MUSHROOM_0);
        event.register(StumpBakedModel.MUSHROOM_1);
        event.register(StumpBakedModel.MUSHROOM_2);
        event.register(StumpBakedModel.MUSHROOM_3);
    }

//    @SubscribeEvent
//    public static void registerModels(ModelRegistryEvent event) { //TODO: Render types
//        RenderType mipped = RenderType.cutoutMipped();
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_LEAVES.get(), mipped);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_LEAVES.get(), mipped);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_LEAVES.get(), mipped);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_LEAVES.get(), mipped);
//        RenderType cutout = RenderType.cutout();
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.TURNIPS.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CUCUMBERS.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.STRAWBERRIES.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CABBAGES.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ONIONS.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.TOMATOES.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CORN.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PINEAPPLES.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.EGGPLANTS.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SPINACH.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SWEET_POTATOES.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.GREEN_PEPPERS.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_SAPLING.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_SAPLING.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_SAPLING.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_SAPLING.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_FRUIT.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_FRUIT.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_FRUIT.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_FRUIT.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SEED_MAKER.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.OLD_SPRINKLER.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.IRON_SPRINKLER.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.OAK_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SPRUCE_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BIRCH_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.JUNGLE_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ACACIA_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.DARK_OAK_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CRIMSON_STUMP.get(), cutout);
//        RenderTypeLookup.setRenderLayer(HorticultureBlocks.WARPED_STUMP.get(), cutout);
//    }

    @SubscribeEvent
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {
        BlockColor block = (state, worldIn, pos, tintIndex) -> tintIndex == 0 ?
                (worldIn != null && pos != null ? BiomeColors.getAverageFoliageColor(worldIn, pos) : FoliageColor.getDefaultColor()) : -1;
        event.register(block, HorticultureBlocks.APPLE_LEAVES.get(), HorticultureBlocks.ORANGE_LEAVES.get(), HorticultureBlocks.PEACH_LEAVES.get(),
                HorticultureBlocks.BANANA_LEAVES.get());
    }

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, index) -> FoliageColor.getDefaultColor(), HorticultureBlocks.APPLE_LEAVES.get(), HorticultureBlocks.ORANGE_LEAVES.get(),
                HorticultureBlocks.PEACH_LEAVES.get(), HorticultureBlocks.BANANA_LEAVES.get());
    }

    private static void replaceLeavesModel(Map<ResourceLocation, BakedModel> registry, String block, BakedModel model) {
        BakedModel existing = registry.get(new ModelResourceLocation(new ResourceLocation(Horticulture.MODID, block), "inventory"));
        if (!(existing instanceof FruitLeavesBakedModel)) {
            registry.put(new ModelResourceLocation(new ResourceLocation(Horticulture.MODID, block), "inventory"), model);
            for (int distance = 0; distance <= 7; distance++) {
                for (int season = 0; season <= 1; season++) {
                    for (int persistent = 0; persistent <= 1; persistent++) {
                        registry.put(new ModelResourceLocation(new ResourceLocation(Horticulture.MODID, block), "distance=" + distance + ",in_season=" + (season == 1) + ",persistent=" + (persistent == 1)), model);
                    }
                }
            }
        }
    }

    private static void replaceStumpModel(Map<ResourceLocation, BakedModel> registry, Block stump) {
        for (BlockState blockState : stump.getStateDefinition().getPossibleStates()) {
            ModelResourceLocation mrl = BlockModelShaper.stateToModelLocation(blockState);
            BakedModel existingModel = registry.get(mrl);
            if (!(existingModel instanceof StumpBakedModel))
                registry.put(mrl, new StumpBakedModel(existingModel));
        }
    }

    @SubscribeEvent
    public static void onBaking(ModelEvent.ModifyBakingResult event) {
        Map<ResourceLocation, BakedModel> registry = event.getModels();
        BakedModel oakLeaves = registry.get(new ModelResourceLocation("minecraft", "oak_leaves", "inventory"));
        BakedModel jungleLeaves = registry.get(new ModelResourceLocation("minecraft", "jungle_leaves", "inventory"));
        BakedModel appleLeaves = registry.get(new ModelResourceLocation("horticulture", "apple_leaves", "inventory"));
        BakedModel orangeLeaves = registry.get(new ModelResourceLocation("horticulture", "orange_leaves", "inventory"));
        BakedModel peachLeaves = registry.get(new ModelResourceLocation("horticulture", "peach_leaves", "inventory"));
        BakedModel bananaLeaves = registry.get(new ModelResourceLocation("horticulture", "banana_leaves", "inventory"));
        ResourceLocation appleEmpty = new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_empty");
        ResourceLocation orangeEmpty = new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_empty");
        ResourceLocation peachEmpty = new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_empty");
        ResourceLocation bananaEmpty = new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_empty");
        ResourceLocation appleFlowering = new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_flowering");
        ResourceLocation orangeFlowering = new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_flowering");
        ResourceLocation peachFlowering = new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_flowering");
        ResourceLocation bananaFlowering = new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_flowering");
        FruitLeavesBakedModel appleModel = new FruitLeavesBakedModel(appleLeaves, oakLeaves, appleFlowering, appleEmpty);
        FruitLeavesBakedModel orangeModel = new FruitLeavesBakedModel(orangeLeaves, oakLeaves, orangeFlowering, orangeEmpty);
        FruitLeavesBakedModel peachModel = new FruitLeavesBakedModel(peachLeaves, oakLeaves, peachFlowering, peachEmpty);
        FruitLeavesBakedModel bananaModel = new FruitLeavesBakedModel(bananaLeaves, jungleLeaves, bananaFlowering, bananaEmpty) {
            @Override
            public int getTintIndex(BakedQuad quad) {
                return 0;
            }
        };

        replaceLeavesModel(registry, "apple_leaves", appleModel);
        replaceLeavesModel(registry, "orange_leaves", orangeModel);
        replaceLeavesModel(registry, "peach_leaves", peachModel);
        replaceLeavesModel(registry, "banana_leaves", bananaModel);
        replaceStumpModel(registry, HorticultureBlocks.OAK_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.SPRUCE_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.BIRCH_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.JUNGLE_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.ACACIA_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.DARK_OAK_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.CHERRY_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.MANGROVE_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.CRIMSON_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.WARPED_STUMP.get());
    }
}
