package uk.joshiejack.horticulture.client.renderer.block.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.data.IModelData;
import uk.joshiejack.horticulture.tile.AbstractStumpTileEntity;
import uk.joshiejack.penguinlib.client.renderer.block.MergeBakedModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unused")
public class StumpBakedModel extends MergeBakedModel {
    private final Int2ObjectMap<Map<BlockState, Map<Direction, List<BakedQuad>>>> models = new Int2ObjectOpenHashMap<>();
    private final IBakedModel[] mushroom_models;

    public StumpBakedModel(IBakedModel parent, IBakedModel[] mushroom_models) {
        super(parent);
        this.mushroom_models = mushroom_models;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random random, @Nonnull IModelData data) {
        if (data.hasProperty(AbstractStumpTileEntity.MUSHROOM_BLOCKSTATE)) {
            BlockState mushroom = data.getData(AbstractStumpTileEntity.MUSHROOM_BLOCKSTATE);
            int stage = data.getData(AbstractStumpTileEntity.MUSHROOM_STAGE);
            if (!models.containsKey(stage)) {
                models.put(stage, Maps.newHashMap());
            }

            Map<BlockState, Map<Direction, List<BakedQuad>>> models = this.models.get(stage);
            if (!models.containsKey(mushroom)) {
                models.put(mushroom, Maps.newHashMap());
            }

            Map<Direction, List<BakedQuad>> map = models.get(mushroom);
            if (!map.containsKey(side)) {
                List<BakedQuad> quads = Lists.newArrayList(super.getQuads(state, side, random));
                TextureAtlasSprite texture = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getParticleIcon(mushroom);
                if (stage > 0) {
                    mushroom_models[stage - 1].getQuads(state, side, random)
                            .forEach(quad -> quads.add(new BakedQuad(quad.getVertices(), quad.getTintIndex(), quad.getDirection(), texture, quad.isShade())));
                }

                map.put(side, ImmutableList.copyOf(quads));
            }

            //Yes my love
            return map.get(side);
        } else return super.getQuads(state, side, random);
    }

}
