package uk.joshiejack.horticulture.data;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.item.HorticultureItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("ConstantConditions")
public class HorticultureItemTags extends ItemTagsProvider {
    public static final TagKey<Item> STUMPS = ItemTags.create(new ResourceLocation(Horticulture.MODID, "stump"));
    public static final TagKey<Item> NETHER_STUMP = ItemTags.create(new ResourceLocation(Horticulture.MODID, "nether_stump"));

    public HorticultureItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> blockLookup, ExistingFileHelper existingFileHelper) {
        super(output, provider, blockLookup, Horticulture.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(STUMPS).add(HorticultureBlocks.OAK_STUMP.asItem(), HorticultureBlocks.BIRCH_STUMP.asItem(), HorticultureBlocks.SPRUCE_STUMP.asItem(),
                HorticultureBlocks.JUNGLE_STUMP.asItem(), HorticultureBlocks.ACACIA_STUMP.asItem(), HorticultureBlocks.DARK_OAK_STUMP.asItem());
        tag(NETHER_STUMP).add(HorticultureBlocks.CRIMSON_STUMP.asItem(), HorticultureBlocks.WARPED_STUMP.asItem());
        List<Item> items = Lists.newArrayList(HorticultureItems.BANANA, HorticultureItems.CABBAGE,
                HorticultureItems.CORN, HorticultureItems.CUCUMBER, HorticultureItems.EGGPLANT,
                HorticultureItems.GREEN_PEPPER, HorticultureItems.ONION, HorticultureItems.ORANGE, HorticultureItems.PEACH,
                HorticultureItems.PINEAPPLE, HorticultureItems.SPINACH, HorticultureItems.STRAWBERRY, HorticultureItems.TOMATO,
                HorticultureItems.TURNIP, HorticultureItems.SWEET_POTATO).stream().map(DeferredHolder::get).toList();
        items.forEach(item -> {
            tag(Tags.Items.CROPS).add(item);
            tag(ItemTags.create(new ResourceLocation("forge", "crops/"
                    + BuiltInRegistries.ITEM.getKey(item).getPath()))).add(item);
            String type = type(item);
            if (type.equals("seeds") || type.equals("starter")) {
                Item seeds = type.equals("seeds") ? BuiltInRegistries.ITEM.get(new ResourceLocation(Horticulture.MODID, BuiltInRegistries.ITEM.getKey(item).getPath() + "_seeds"))
                        : BuiltInRegistries.ITEM.get(new ResourceLocation(Horticulture.MODID, BuiltInRegistries.ITEM.getKey(item).getPath() + "_starter"));
                assert seeds != null;
                tag(Tags.Items.SEEDS).add(seeds);
                tag(ItemTags.create(new ResourceLocation("forge", "seeds/"
                        + BuiltInRegistries.ITEM.getKey(item).getPath()))).add(seeds);
            }

            if (type.equals("sapling")) {
                Item sapling = BuiltInRegistries.ITEM.get(new ResourceLocation(Horticulture.MODID, BuiltInRegistries.ITEM.getKey(item).getPath() + "_sapling"));
                Item leaves = BuiltInRegistries.ITEM.get(new ResourceLocation(Horticulture.MODID, BuiltInRegistries.ITEM.getKey(item).getPath() + "_leaves"));
                assert sapling != null;
                assert leaves != null;
                tag(ItemTags.SAPLINGS).add(sapling);
                tag(ItemTags.LEAVES).add(leaves);
                tag(ItemTags.create(new ResourceLocation("forge", "saplings/"
                        + BuiltInRegistries.ITEM.getKey(item).getPath()))).add(sapling);
            }
        });
    }

    private static String type(Item item) {
        String name = BuiltInRegistries.ITEM.getKey(item).getPath();
        return (name.equals("banana") || name.equals("orange") || name.equals("peach")) ? "sapling" : "seeds";
    }
}
