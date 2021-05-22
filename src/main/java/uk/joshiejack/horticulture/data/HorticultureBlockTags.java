package uk.joshiejack.horticulture.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import uk.joshiejack.horticulture.Horticulture;

import javax.annotation.Nullable;

public class HorticultureBlockTags extends BlockTagsProvider {
    public HorticultureBlockTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

    }
}
