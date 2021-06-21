package uk.joshiejack.horticulture.data;

import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.tileentity.AbstractSprinklerTileEntity;

import javax.annotation.Nullable;

public class HorticultureBlockTags extends BlockTagsProvider {
    public HorticultureBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ForgeRegistries.BLOCKS.forEach(block -> {
            if (block == Blocks.SWEET_BERRY_BUSH)
                tag(AbstractSprinklerTileEntity.SPRINKLER_GROWABLE).add(block);
        });
    }
}
