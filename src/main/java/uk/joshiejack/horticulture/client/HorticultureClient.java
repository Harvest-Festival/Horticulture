package uk.joshiejack.horticulture.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.client.renderer.FruitLeavesBakedModel;
import uk.joshiejack.horticulture.client.renderer.SeedMakerTileEntityRenderer;
import uk.joshiejack.horticulture.client.renderer.StumpBakedModel;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tileentity.HorticultureTileEntities;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Horticulture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorticultureClient {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ClientRegistry.bindTileEntityRenderer(HorticultureTileEntities.SEED_MAKER.get(), (SeedMakerTileEntityRenderer::new));
        ModelLoader.addSpecialModel(StumpBakedModel.MUSHROOM_0);
        ModelLoader.addSpecialModel(StumpBakedModel.MUSHROOM_1);
        ModelLoader.addSpecialModel(StumpBakedModel.MUSHROOM_2);
        ModelLoader.addSpecialModel(StumpBakedModel.MUSHROOM_3);
        RenderType mipped = RenderType.cutoutMipped();
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_LEAVES.get(), mipped);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_LEAVES.get(), mipped);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_LEAVES.get(), mipped);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_LEAVES.get(), mipped);
        RenderType cutout = RenderType.cutout();
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.TURNIPS.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CUCUMBERS.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.STRAWBERRIES.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CABBAGES.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ONIONS.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.TOMATOES.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CORN.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PINEAPPLES.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.EGGPLANTS.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SPINACH.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SWEET_POTATOES.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.GREEN_PEPPERS.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_SAPLING.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BANANA_FRUIT.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.APPLE_FRUIT.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ORANGE_FRUIT.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.PEACH_FRUIT.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SEED_MAKER.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.OLD_SPRINKLER.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.IRON_SPRINKLER.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.OAK_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.SPRUCE_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.BIRCH_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.JUNGLE_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.ACACIA_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.DARK_OAK_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.CRIMSON_STUMP.get(), cutout);
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.WARPED_STUMP.get(), cutout);
    }

    @SubscribeEvent
    public static void onBlockColors(ColorHandlerEvent.Block event) {
        IBlockColor block = (state, worldIn, pos, tintIndex) -> tintIndex == 0 ?
                (worldIn != null && pos != null ? BiomeColors.getAverageFoliageColor(worldIn, pos) : FoliageColors.getDefaultColor()) : -1;
        event.getBlockColors().register(block, HorticultureBlocks.APPLE_LEAVES.get());
        event.getBlockColors().register(block, HorticultureBlocks.ORANGE_LEAVES.get());
        event.getBlockColors().register(block, HorticultureBlocks.PEACH_LEAVES.get());
        event.getBlockColors().register(block, HorticultureBlocks.BANANA_LEAVES.get());
    }

    @SubscribeEvent
    public static void onItemColors(ColorHandlerEvent.Item event) {
        IItemColor item = (stack, index) -> FoliageColors.getDefaultColor();
        event.getItemColors().register(item, HorticultureItems.APPLE_LEAVES.get());
        event.getItemColors().register(item, HorticultureItems.ORANGE_LEAVES.get());
        event.getItemColors().register(item, HorticultureItems.PEACH_LEAVES.get());
        event.getItemColors().register(item, HorticultureItems.BANANA_LEAVES.get());
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_empty"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_empty"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_empty"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_empty"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_flowering"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_flowering"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_flowering"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_flowering"));
    }

    private static void replaceLeavesModel(Map<ResourceLocation, IBakedModel> registry, String block, IBakedModel model) {
        IBakedModel existing = registry.get(new ModelResourceLocation("horticulture:" + block + "#inventory"));
        if (!(existing instanceof FruitLeavesBakedModel)) {
            registry.put(new ModelResourceLocation("horticulture:" + block + "#inventory"), model);
            for (int distance = 0; distance <= 7; distance++) {
                for (int season = 0; season <= 1; season++) {
                    for (int persistent = 0; persistent <= 1; persistent++) {
                        registry.put(new ModelResourceLocation("horticulture:" + block + "#distance=" + distance + ",in_season=" + (season == 1) + ",persistent=" + (persistent == 1)), model);
                    }
                }
            }
        }
    }

    private static void replaceStumpModel(Map<ResourceLocation, IBakedModel> registry, Block stump) {
        for (BlockState blockState : stump.getStateDefinition().getPossibleStates()) {
            ModelResourceLocation mrl = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = registry.get(mrl);
            if (!(existingModel instanceof StumpBakedModel))
                registry.put(mrl, new StumpBakedModel(existingModel));
        }
    }

    @SubscribeEvent
    public static void onBaking(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel oakLeaves = event.getModelManager().getModel(new ModelResourceLocation("minecraft:oak_leaves#inventory"));
        IBakedModel jungleLeaves = event.getModelManager().getModel(new ModelResourceLocation("minecraft:jungle_leaves#inventory"));
        IBakedModel appleLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:apple_leaves#inventory"));
        IBakedModel orangeLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:orange_leaves#inventory"));
        IBakedModel peachLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:peach_leaves#inventory"));
        IBakedModel bananaLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:banana_leaves#inventory"));
        AtlasTexture textureGetter = Minecraft.getInstance().getModelManager().getAtlas(PlayerContainer.BLOCK_ATLAS);
        TextureAtlasSprite appleEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_empty"));
        TextureAtlasSprite orangeEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_empty"));
        TextureAtlasSprite peachEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_empty"));
        TextureAtlasSprite bananaEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_empty"));
        TextureAtlasSprite appleFlowering = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_flowering"));
        TextureAtlasSprite orangeFlowering = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_flowering"));
        TextureAtlasSprite peachFlowering = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_flowering"));
        TextureAtlasSprite bananaFlowering = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_flowering"));
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
        replaceStumpModel(registry, HorticultureBlocks.CRIMSON_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.WARPED_STUMP.get());
    }
}
