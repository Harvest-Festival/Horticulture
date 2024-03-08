package uk.joshiejack.horticulture.client.renderer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.AbstractStumpBlock;
import uk.joshiejack.horticulture.world.block.entity.AbstractStumpBlockEntity;
import uk.joshiejack.penguinlib.util.helper.BakedModelHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class StumpBakedModel extends BakedModelWrapper<BakedModel> {
    public static final ResourceLocation MUSHROOM_0 = new ResourceLocation(Horticulture.MODID, "block/mushroom_0");
    public static final ResourceLocation MUSHROOM_1 = new ResourceLocation(Horticulture.MODID, "block/mushroom_1");
    public static final ResourceLocation MUSHROOM_2 = new ResourceLocation(Horticulture.MODID, "block/mushroom_2");
    public static final ResourceLocation MUSHROOM_3 = new ResourceLocation(Horticulture.MODID, "block/mushroom_3");
    private static BakedModel[] mushroom_models;
    private final Int2ObjectMap<Map<BlockState, Map<Direction, List<BakedQuad>>>> models = new Int2ObjectOpenHashMap<>();

    public StumpBakedModel(BakedModel parent) {
        super(parent);
    }

    public BakedModel getMushroomMode(int stage) {
        if (mushroom_models == null) {
            ModelManager manager = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getModelManager();
            mushroom_models = new BakedModel[]{
                    manager.getModel(MUSHROOM_0), manager.getModel(MUSHROOM_1),
                    manager.getModel(MUSHROOM_2), manager.getModel(MUSHROOM_3)
            };
        }

        return mushroom_models[stage];
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource random, @Nonnull ModelData data, @org.jetbrains.annotations.Nullable RenderType renderType) {
        if (state != null && data.has(AbstractStumpBlockEntity.MUSHROOM_BLOCKSTATE)) {
            BlockState mushroom = data.get(AbstractStumpBlockEntity.MUSHROOM_BLOCKSTATE);
            if (mushroom == null || mushroom.getBlock() == Blocks.AIR) return super.getQuads(state, side, random, data, renderType);
            int stage = state.getValue(AbstractStumpBlock.AGE);
            if (!models.containsKey(stage))
                models.put(stage, new HashMap<>());
            Map<BlockState, Map<Direction, List<BakedQuad>>> models = this.models.get(stage);
            if (!models.containsKey(mushroom))
                models.put(mushroom, new HashMap<>());
            Map<Direction, List<BakedQuad>> map = models.get(mushroom);
            if (!map.containsKey(side)) {
                List<BakedQuad> quads = Lists.newArrayList(super.getQuads(state, side, random)); //Grab the base stump model
                TextureAtlasSprite texture = Minecraft.getInstance().getBlockRenderer().getBlockModel(mushroom).getParticleIcon(ModelData.EMPTY);
                getMushroomMode(stage).getQuads(state, side, random, data, renderType).forEach(quad -> quads.add(BakedModelHelper.retexture(quad, texture)));
                map.put(side, ImmutableList.copyOf(quads));
            }

            return map.get(side);
        } else return super.getQuads(state, side, random);
    }
}
