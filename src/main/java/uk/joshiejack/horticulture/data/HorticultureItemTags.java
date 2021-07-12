package uk.joshiejack.horticulture.data;

import com.google.common.collect.Lists;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.data.TagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.item.HorticultureItems;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
public class HorticultureItemTags extends ItemTagsProvider {
    public static final ITag.INamedTag<Item> STUMPS = ItemTags.createOptional(new ResourceLocation(Horticulture.MODID, "stump"));
    public static final ITag.INamedTag<Item> NETHER_STUMP = ItemTags.createOptional(new ResourceLocation(Horticulture.MODID, "nether_stump"));

    public HorticultureItemTags(DataGenerator generator, BlockTagsProvider blockTagProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, blockTagProvider, Horticulture.MODID, existingFileHelper);
    }

    @Override
    public void addTags() {
        tag(STUMPS).add(HorticultureItems.OAK_STUMP.get(), HorticultureItems.BIRCH_STUMP.get(), HorticultureItems.SPRUCE_STUMP.get(),
                HorticultureItems.JUNGLE_STUMP.get(), HorticultureItems.ACACIA_STUMP.get(), HorticultureItems.DARK_OAK_STUMP.get());
        tag(NETHER_STUMP).add(HorticultureItems.CRIMSON_STUMP.get(), HorticultureItems.WARPED_STUMP.get());
        TagsProvider.Builder<Item> cropsTag = tag(Tags.Items.CROPS);
        TagsProvider.Builder<Item> seedsTag = tag(Tags.Items.SEEDS);
        TagsProvider.Builder<Item> saplingsTag = tag(ItemTags.SAPLINGS);
        TagsProvider.Builder<Item> leavesTag = tag(ItemTags.LEAVES);
        List<Item> items = Lists.newArrayList(HorticultureItems.BANANA, HorticultureItems.CABBAGE,
                HorticultureItems.CORN, HorticultureItems.CUCUMBER, HorticultureItems.EGGPLANT,
                HorticultureItems.GREEN_PEPPER, HorticultureItems.ONION, HorticultureItems.ORANGE, HorticultureItems.PEACH,
                HorticultureItems.PINEAPPLE, HorticultureItems.SPINACH, HorticultureItems.STRAWBERRY, HorticultureItems.TOMATO,
                HorticultureItems.TURNIP, HorticultureItems.SWEET_POTATO).stream().map(RegistryObject::get).collect(Collectors.toList());
        items.forEach(item -> {
            cropsTag.add(item);
            tag(ItemTags.createOptional(new ResourceLocation("forge", "crops/"
                    + item.getRegistryName().getPath()))).add(item);
            String type = type(item);
            if (type.equals("seeds") || type.equals("starter")) {
                Item seeds = type.equals("seeds") ? ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, item.getRegistryName().getPath() + "_seeds"))
                        : ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, item.getRegistryName().getPath() + "_starter"));
                assert seeds != null;
                seedsTag.add(seeds);
                tag(ItemTags.createOptional(new ResourceLocation("forge", "seeds/"
                        + item.getRegistryName().getPath()))).add(seeds);
            }

            if (type.equals("sapling")) {
                Item sapling = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, item.getRegistryName().getPath() + "_sapling"));
                Item leaves = ForgeRegistries.ITEMS.getValue(new ResourceLocation(Horticulture.MODID, item.getRegistryName().getPath() + "_leaves"));
                assert sapling != null;
                assert leaves != null;
                saplingsTag.add(sapling);
                leavesTag.add(leaves);
                tag(ItemTags.createOptional(new ResourceLocation("forge", "saplings/"
                        + item.getRegistryName().getPath()))).add(sapling);
            }
        });

        saplingsTag.add(HorticultureItems.APPLE_SAPLING.get());
        leavesTag.add(HorticultureItems.APPLE_LEAVES.get());
    }

    private static String type(Item item) {
        String name = item.getRegistryName().getPath();
        return (name.equals("banana") || name.equals("orange") || name.equals("peach")) ? "sapling" : "seeds";
    }
}
