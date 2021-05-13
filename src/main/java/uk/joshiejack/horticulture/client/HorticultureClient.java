package uk.joshiejack.horticulture.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
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
import uk.joshiejack.horticulture.client.renderer.StumpBakedModel;
import uk.joshiejack.horticulture.client.renderer.SeedMakerTileEntityRenderer;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tile.HorticultureTileEntities;

import java.util.Map;

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
        RenderTypeLookup.setRenderLayer(HorticultureBlocks.GRAPES.get(), cutout);
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
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_solid"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_solid"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_solid"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast"));
    }

    private static void replaceLeavesModel(Map<ResourceLocation, IBakedModel> registry, String block, IBakedModel model) {
        registry.put(new ModelResourceLocation("horticulture:" + block + "#inventory"), model);
        for (int distance = 0; distance <= 7; distance++) {
            for (int season = 0; season <= 1; season++) {
                for (int persistent = 0; persistent <= 1; persistent++) {
                    registry.put(new ModelResourceLocation("horticulture:" + block + "#distance=" + distance + ",in_season=" + (season == 1) + ",persistent=" + (persistent == 1)), model);
                }
            }
        }
    }

    private static void replaceStumpModel(Map<ResourceLocation, IBakedModel> registry, Block stump) {
        for (BlockState blockState : stump.getStateDefinition().getPossibleStates()) {
            ModelResourceLocation mrl = BlockModelShapes.stateToModelLocation(blockState);
            IBakedModel existingModel = registry.get(mrl);
            registry.put(mrl, new StumpBakedModel(existingModel));
        }
    }

    @SubscribeEvent
    public static void onBaking(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        IBakedModel oakLeaves = event.getModelManager().getModel(new ModelResourceLocation("minecraft:oak_leaves#inventory"));
        IBakedModel appleLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:apple_leaves#inventory"));
        IBakedModel orangeLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:orange_leaves#inventory"));
        IBakedModel peachLeaves = event.getModelManager().getModel(new ModelResourceLocation("horticulture:peach_leaves#inventory"));
        AtlasTexture textureGetter = Minecraft.getInstance().getModelManager().getAtlas(PlayerContainer.BLOCK_ATLAS);
        TextureAtlasSprite appleSolid = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_solid"));
        TextureAtlasSprite orangeSolid = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_solid"));
        TextureAtlasSprite peachSolid = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_solid"));
        TextureAtlasSprite appleEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast"));
        TextureAtlasSprite orangeEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast"));
        TextureAtlasSprite peachEmpty = textureGetter.getSprite(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast"));
        FruitLeavesBakedModel appleModel = new FruitLeavesBakedModel(appleLeaves, oakLeaves, appleSolid, appleEmpty);
        FruitLeavesBakedModel orangeModel = new FruitLeavesBakedModel(orangeLeaves, oakLeaves, orangeSolid, orangeEmpty);
        FruitLeavesBakedModel peachModel = new FruitLeavesBakedModel(peachLeaves, oakLeaves, peachSolid, peachEmpty);
        replaceLeavesModel(registry, "apple_leaves", appleModel);
        replaceLeavesModel(registry, "orange_leaves", orangeModel);
        replaceLeavesModel(registry, "peach_leaves", peachModel);
        replaceStumpModel(registry, HorticultureBlocks.OAK_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.SPRUCE_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.BIRCH_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.JUNGLE_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.ACACIA_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.DARK_OAK_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.CRIMSON_STUMP.get());
        replaceStumpModel(registry, HorticultureBlocks.WARPED_STUMP.get());

        for (ResourceLocation location : registry.keySet())
            Horticulture.LOGGER.info("Model bake location: " + location);
    }
}
