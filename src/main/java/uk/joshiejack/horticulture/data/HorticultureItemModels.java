package uk.joshiejack.horticulture.data;

import joptsimple.internal.Strings;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.item.HorticultureItems;

import java.util.Objects;

public class HorticultureItemModels extends ItemModelProvider {
    public HorticultureItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Horticulture.MODID, existingFileHelper);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void registerModels() {
        HorticultureItems.ITEMS.getEntries().stream()
                .map(DeferredHolder::get)
                .forEach(item -> {
                    String path = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
                    if (item instanceof BlockItem && !path.contains("seeds"))
                        if (path.contains("sapling"))
                            singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("block/saplings/" + path.replace("_sapling", "")));
                        else if (path.contains("fruit"))
                            singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("block/crops/" + path + "_0"));
                        else
                            getBuilder(path).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
                    else {
                        String subdir =
                                isFruit(BuiltInRegistries.ITEM.getKey(item).getPath()) || (item.getFoodProperties() != null && item.getFoodProperties().getNutrition() < 3) ? "crops/" :
                                item.isEdible() && item.getFoodProperties().getNutrition() >= 3 ? "meals/" :
                                        BuiltInRegistries.ITEM.getKey(item).getPath().contains("seeds") ? "seeds/" :
                                                Strings.EMPTY;
                        singleTexture(path, mcLoc("item/generated"), "layer0", modLoc("item/" + subdir + path.replace("_seeds", "")));
                    }
                });
    }

    private boolean isFruit(String name) {
        return name.equals("banana") || name.equals("orange") || name.equals("peach");
    }
}
