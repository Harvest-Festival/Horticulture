package uk.joshiejack.horticulture.client.renderer;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.BakedModelWrapper;
import net.neoforged.neoforge.client.model.data.ModelData;
import uk.joshiejack.horticulture.world.block.AbstractFruitTreeLeavesBlock;
import uk.joshiejack.penguinlib.util.helper.BakedModelHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class FruitLeavesBakedModel extends BakedModelWrapper<BakedModel> {
    private final Map<Direction, List<BakedQuad>> quads_fancy_in_season = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_simple_in_season = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_fancy = new HashMap<>();
    private final Map<Direction, List<BakedQuad>> quads_simple = new HashMap<>();
    private final ResourceLocation floweringLocation;
    private final ResourceLocation emptyLocation;
    private TextureAtlasSprite flowering;
    private TextureAtlasSprite empty;
    private final BakedModel base;

    public FruitLeavesBakedModel(BakedModel parent, BakedModel base, ResourceLocation floweringLocation, ResourceLocation emptyLocation) {
        super(parent);
        this.base = base;
        this.floweringLocation = floweringLocation;
        this.emptyLocation = emptyLocation;
    }

    protected TextureAtlasSprite flowering() {
        return (flowering != null) ? flowering : (flowering = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(floweringLocation));
    }

    protected TextureAtlasSprite empty() {
        return (empty != null) ? empty : (empty = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(emptyLocation));
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource random) {
        if (side == null) return super.getQuads(state, null, random);
        Map<Direction, List<BakedQuad>> quads = Minecraft.useFancyGraphics() ? quads_fancy_in_season : quads_simple_in_season;
        if (!quads.containsKey(side)) {
            List<BakedQuad> list = Lists.newArrayList();
            if (Minecraft.useFancyGraphics()) {
                list.addAll(base.getQuads(state, side, random));
                FruitLeavesBakedModel.super.getQuads(state, side, random).stream()
                        .map(quad -> new BakedQuad(quad.getVertices(), getTintIndex(quad), quad.getDirection(), quad.getSprite(), quad.isShade())).forEachOrdered(list::add);
            } else
                BakedModelHelper.cube(flowering(), list); //TODO TEST
            quads.put(side, list);
            return list;
        } else return quads.get(side);
    }

    protected int getTintIndex(BakedQuad quad) {
        return quad.getTintIndex();
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull RandomSource random, @Nonnull ModelData data, @Nullable RenderType renderType) {
        if (side == null) return super.getQuads(state, null, random, data, renderType);
        boolean inSeason = state == null || state.getValue(AbstractFruitTreeLeavesBlock.IN_SEASON);
        Map<Direction, List<BakedQuad>> quads = inSeason ? Minecraft.useFancyGraphics() ? quads_fancy_in_season : quads_simple_in_season :
                Minecraft.useFancyGraphics() ? quads_fancy : quads_simple;
        if (!quads.containsKey(side)) {
            List<BakedQuad> list = Lists.newArrayList();
            if (Minecraft.useFancyGraphics()) {
                list.addAll(base.getQuads(state, side, random, data, renderType));
                FruitLeavesBakedModel.super.getQuads(state, side, random).stream()
                        .map(quad -> new BakedQuad(quad.getVertices(), getTintIndex(quad), quad.getDirection(), quad.getSprite(), quad.isShade())).forEachOrdered(list::add);
            } else
                BakedModelHelper.cube(inSeason ? flowering() : empty(), list);
            quads.put(side, list);
            return list;
        } else return quads.get(side);
    }
}


