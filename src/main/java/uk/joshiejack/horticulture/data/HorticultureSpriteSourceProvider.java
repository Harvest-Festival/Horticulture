package uk.joshiejack.horticulture.data;

import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;
import uk.joshiejack.horticulture.Horticulture;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class HorticultureSpriteSourceProvider extends SpriteSourceProvider {
    public HorticultureSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper fileHelper) {
        super(output, lookupProvider, Horticulture.MODID, fileHelper);
    }

    @Override
    protected void gather() {
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_empty"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_empty"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_empty"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_empty"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/apple_fast_flowering"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/orange_fast_flowering"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/peach_fast_flowering"), Optional.empty()));
        atlas(SpriteSourceProvider.BLOCKS_ATLAS).addSource(new SingleFile(new ResourceLocation(Horticulture.MODID, "block/leaves/banana_fast_flowering"), Optional.empty()));
    }
}
