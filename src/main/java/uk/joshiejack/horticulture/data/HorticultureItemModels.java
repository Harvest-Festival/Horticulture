package uk.joshiejack.horticulture.data;

import joptsimple.internal.Strings;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.item.HorticultureItems;

import java.util.Objects;

public class HorticultureItemModels extends ItemModelProvider {
    public HorticultureItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Horticulture.MODID, existingFileHelper);
    }

    private void registerModels(DeferredRegister<Item> items) {

    }

    @Override
    protected void registerModels() {
        HorticultureItems.ITEMS.getEntries().stream()
                .map(RegistryObject::get)
                .forEach(item -> {
                    String path = Objects.requireNonNull(item.getRegistryName()).getPath();
                    if (item instanceof BlockItem && !path.contains("seeds"))
                        if (path.contains("fruit"))
                            singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("block/crops/" + path + "_0"));
                        else
                            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
                    else {
                        String subdir =
                                isFruit(item.getRegistryName().getPath()) || (item.getFoodProperties() != null && item.getFoodProperties().getNutrition() < 3) ? "crops/" :
                                item.getFoodProperties() != null && item.getFoodProperties().getNutrition() >= 3 ? "meals/" :
                                        item.getRegistryName().getPath().contains("seeds") ? "seeds/" :
                                                Strings.EMPTY;
                        singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("item/" + subdir + path.replace("_seeds", "")));
                    }
                });
    }

    private boolean isFruit(String name) {
        return name.equals("banana") || name.equals("grape") || name.equals("orange") || name.equals("peach");
    }
}
