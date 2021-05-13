package uk.joshiejack.horticulture.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.client.renderer.tile.SeedMakerTileEntityRenderer;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tile.HorticultureTileEntities;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Horticulture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorticultureClient {
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ClientRegistry.bindTileEntityRenderer(HorticultureTileEntities.SEED_MAKER.get(), (SeedMakerTileEntityRenderer::new));
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
}
