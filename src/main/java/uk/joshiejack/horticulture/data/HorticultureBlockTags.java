package uk.joshiejack.horticulture.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.block.entity.AbstractSprinklerBlockEntity;

import java.util.concurrent.CompletableFuture;

public class HorticultureBlockTags extends BlockTagsProvider {
    public HorticultureBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(AbstractSprinklerBlockEntity.SPRINKLER_GROWABLE).add(Blocks.SWEET_BERRY_BUSH);
        tag(BlockTags.LEAVES).add(HorticultureBlocks.APPLE_LEAVES.get(), HorticultureBlocks.BANANA_LEAVES.get(),
                HorticultureBlocks.ORANGE_LEAVES.get(), HorticultureBlocks.PEACH_LEAVES.get());
        tag(BlockTags.SAPLINGS).add(HorticultureBlocks.APPLE_SAPLING.get(), HorticultureBlocks.BANANA_SAPLING.get(),
                HorticultureBlocks.ORANGE_SAPLING.get(), HorticultureBlocks.PEACH_SAPLING.get());
    }
}
