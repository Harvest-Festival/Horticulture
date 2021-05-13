package uk.joshiejack.horticulture.client.renderer.block.statemap;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import uk.joshiejack.horticulture.Horticulture;

import java.util.Map;


@Mod.EventBusSubscriber(modid = Horticulture.MODID)
public class StateMapperLeaves {
    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation(Horticulture.MODID, "blocks/leaves/oak_black"));
        event.addSprite(new ResourceLocation(Horticulture.MODID, "blocks/leaves/jungle_black"));
    }

    @SubscribeEvent
    public static void onBaking(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();
        for (ResourceLocation location : registry.keySet())
            Horticulture.LOGGER.info("Model bake location: " + location);

        /*
        IBakedModel oak = registry.get(new ModelResourceLocation("minecraft:oak_leaves#normal"));
        TextureAtlasSprite spriteOak = Minecraft.getInstance().getTextureMapBlocks().getAtlasSprite("horticulture:blocks/leaves/oak_black");
        for (BlockLeavesTemperate.Fruit leaves: BlockLeavesTemperate.Fruit.values()) {
            BlockState state = LEAVES_TEMPERATE.getStateFromEnum(leaves);
            registry.put(getModelResourceLocation(state), new BakedLeaves(registry.getObject(getModelResourceLocation(state)), oak, spriteOak));
        }

        IBakedModel jungle = registry.getObject(new ModelResourceLocation("minecraft:jungle_leaves#normal"));
        TextureAtlasSprite spriteJungle = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("horticulture:blocks/leaves/jungle_black");
        for (BlockLeavesTropical.Fruit leaves: BlockLeavesTropical.Fruit.values()) {
            BlockState state = LEAVES_TROPICAL.getStateFromEnum(leaves);
            registry.putObject(getModelResourceLocation(state), new BakedLeaves(registry.getObject(getModelResourceLocation(state)), jungle, spriteJungle));
        } */
    }
}
