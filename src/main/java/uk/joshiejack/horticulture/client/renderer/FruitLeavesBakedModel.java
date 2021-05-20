package uk.joshiejack.horticulture.client.renderer;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.BakedModelWrapper;
import net.minecraftforge.client.model.data.IModelData;
import uk.joshiejack.horticulture.block.FruitTreeLeavesBlock;
import uk.joshiejack.penguinlib.client.helpers.BakedModelHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class FruitLeavesBakedModel extends BakedModelWrapper<IBakedModel> {
    private final Map<Direction, List<BakedQuad>> quads_fancy_in_season = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_simple_in_season = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_fancy = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_simple = new HashMap<>();
    private final TextureAtlasSprite flowering;
    private final TextureAtlasSprite empty;
    private final IBakedModel base;

    public FruitLeavesBakedModel(IBakedModel parent, IBakedModel base, TextureAtlasSprite flowering, TextureAtlasSprite empty) {
        super(parent);
        this.base = base;
        this.flowering = flowering;
        this.empty = empty;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random random) {
        if (side == null) return super.getQuads(state, null, random);
        Map<Direction, List<BakedQuad>> quads = Minecraft.useFancyGraphics() ? quads_fancy_in_season : quads_simple_in_season;
        if (!quads.containsKey(side)) {
            List<BakedQuad> list = Lists.newArrayList();
            if (Minecraft.useFancyGraphics()) {
                list.addAll(base.getQuads(state, side, random));
                FruitLeavesBakedModel.super.getQuads(state, side, random).stream()
                        .map(quad -> new BakedQuad(quad.getVertices(), 0, quad.getDirection(), quad.getSprite(), quad.isShade())).forEachOrdered(list::add);
            } else
                BakedModelHelper.buildCube(side, flowering, list);
            quads.put(side, list);
            return list;
        } else return quads.get(side);
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random random, @Nonnull IModelData data) {
        if (side == null) return super.getQuads(state, null, random, data);
        boolean inSeason = state == null || state.getValue(FruitTreeLeavesBlock.IN_SEASON);
        Map<Direction, List<BakedQuad>> quads = inSeason ? Minecraft.useFancyGraphics() ? quads_fancy_in_season : quads_simple_in_season :
                Minecraft.useFancyGraphics() ? quads_fancy : quads_simple;
        if (!quads.containsKey(side)) {
            List<BakedQuad> list = Lists.newArrayList();
            if (Minecraft.useFancyGraphics()) {
                list.addAll(base.getQuads(state, side, random, data));
                FruitLeavesBakedModel.super.getQuads(state, side, random).stream()
                        .map(quad -> new BakedQuad(quad.getVertices(), 0, quad.getDirection(), quad.getSprite(), quad.isShade())).forEachOrdered(list::add);
            } else
                BakedModelHelper.buildCube(side, inSeason ? flowering : empty, list);
            quads.put(side, list);
            return list;
        } else return quads.get(side);
    }

    @Nonnull
    @Override
    public IBakedModel handlePerspective(@Nonnull ItemCameraTransforms.TransformType cameraTransformType, @Nonnull MatrixStack mat) {
        return ForgeHooksClient.handlePerspective(getBakedModel(), cameraTransformType, mat);
    }
}


