package uk.joshiejack.horticulture.client.renderer.block.model;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import uk.joshiejack.penguinlib.client.renderer.block.MergeBakedModel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unused")
public class BakedLeaves extends MergeBakedModel {
    private final Map<Direction, List<BakedQuad>> quads_fancy = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_simple = new HashMap<>();
    private final TextureAtlasSprite sprite;
    private final IBakedModel base;

    public BakedLeaves(IBakedModel parent, IBakedModel base, TextureAtlasSprite sprite) {
        super(parent);
        this.base = base;
        this.sprite = sprite;
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random random) {
        boolean fancy = Minecraft.getInstance().options.graphicsMode.getId() > 0;
        Map<Direction, List<BakedQuad>> quads = fancy ? quads_fancy : quads_simple;
        if (!quads.containsKey(side)) {
            List<BakedQuad> list = Lists.newArrayList();
            if (fancy) list.addAll(base.getQuads(state, side, random));
            else base.getQuads(state, side, random).stream().map(quad -> new BakedQuad(quad.getVertices(), quad.getTintIndex(), quad.getDirection(), sprite, quad.isShade())).forEachOrdered(list::add);
            BakedLeaves.super.getQuads(state, side, random).stream().map(quad -> new BakedQuad(quad.getVertices(), 1, quad.getDirection(), sprite, quad.isShade())).forEachOrdered(list::add);
            quads.put(side, list);
            return list;
        } else return quads.get(side);
    }

}


