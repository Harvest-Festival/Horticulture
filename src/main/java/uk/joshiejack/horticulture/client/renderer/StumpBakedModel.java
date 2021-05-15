package uk.joshiejack.horticulture.client.renderer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.StumpBlock;
import uk.joshiejack.horticulture.tileentity.AbstractStumpTileEntity;
import uk.joshiejack.penguinlib.client.renderer.block.MergeBakedModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unused")
public class StumpBakedModel extends MergeBakedModel {
    public static final ResourceLocation MUSHROOM_0 = new ResourceLocation(Horticulture.MODID, "block/mushroom_0");
    public static final ResourceLocation MUSHROOM_1 = new ResourceLocation(Horticulture.MODID, "block/mushroom_1");
    public static final ResourceLocation MUSHROOM_2 = new ResourceLocation(Horticulture.MODID, "block/mushroom_2");
    public static final ResourceLocation MUSHROOM_3 = new ResourceLocation(Horticulture.MODID, "block/mushroom_3");
    private static IBakedModel[] mushroom_models;
    private final Int2ObjectMap<Map<BlockState, Map<Direction, List<BakedQuad>>>> models = new Int2ObjectOpenHashMap<>();

    public StumpBakedModel(IBakedModel parent) {
        super(parent);
    }

    public IBakedModel getMushroomMode(int stage) {
        if (mushroom_models == null) {
            ModelManager manager = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getModelManager();
            mushroom_models = new IBakedModel[]{
                    manager.getModel(MUSHROOM_0), manager.getModel(MUSHROOM_1),
                    manager.getModel(MUSHROOM_2), manager.getModel(MUSHROOM_3)
            };
        }

        return mushroom_models[stage];
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random random, @Nonnull IModelData data) {
        if (state != null && data.hasProperty(AbstractStumpTileEntity.MUSHROOM_BLOCKSTATE)) {
            BlockState mushroom = data.getData(AbstractStumpTileEntity.MUSHROOM_BLOCKSTATE);
            if (mushroom == null || mushroom.getBlock() == Blocks.AIR) return super.getQuads(state, side, random, data);
            int stage = state.getValue(StumpBlock.AGE);
            if (!models.containsKey(stage))
                models.put(stage, new HashMap<>());
            Map<BlockState, Map<Direction, List<BakedQuad>>> models = this.models.get(stage);
            if (!models.containsKey(mushroom))
                models.put(mushroom, new HashMap<>());
            Map<Direction, List<BakedQuad>> map = models.get(mushroom);
            if (!map.containsKey(side)) {
                List<BakedQuad> quads = Lists.newArrayList(super.getQuads(state, side, random)); //Grab the base stump model
                TextureAtlasSprite texture = Minecraft.getInstance().getBlockRenderer().getBlockModel(state).getParticleTexture(EmptyModelData.INSTANCE);
                getMushroomMode(stage).getQuads(state, side, random, data).forEach(quad -> quads.add(retexture(quad, texture)));
                map.put(side, ImmutableList.copyOf(quads));
            }

            return map.get(side);
        } else return super.getQuads(state, side, random);
    }
}
