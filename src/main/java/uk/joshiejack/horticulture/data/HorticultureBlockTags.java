package uk.joshiejack.horticulture.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.tileentity.AbstractSprinklerTileEntity;

import javax.annotation.Nullable;

public class HorticultureBlockTags extends BlockTagsProvider {
    public HorticultureBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(AbstractSprinklerTileEntity.SPRINKLER_GROWABLE).add(Blocks.SWEET_BERRY_BUSH);
        tag(BlockTags.LEAVES).add(HorticultureBlocks.APPLE_LEAVES.get(), HorticultureBlocks.BANANA_LEAVES.get(),
                HorticultureBlocks.ORANGE_LEAVES.get(), HorticultureBlocks.PEACH_LEAVES.get());
        tag(BlockTags.SAPLINGS).add(HorticultureBlocks.APPLE_SAPLING.get(), HorticultureBlocks.BANANA_SAPLING.get(),
                HorticultureBlocks.ORANGE_SAPLING.get(), HorticultureBlocks.PEACH_SAPLING.get());
    }
}
