package uk.joshiejack.horticulture;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import uk.joshiejack.horticulture.block.HorticultureBlocks;
import uk.joshiejack.horticulture.crafting.HorticultureRegistries;
import uk.joshiejack.horticulture.data.*;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.horticulture.tileentity.HorticultureTileEntities;
import uk.joshiejack.horticulture.world.HorticultureWorld;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Horticulture.MODID)
public class Horticulture {
    public static final String MODID = "horticulture";
    public static final ItemGroup TAB = new ItemGroup(MODID) {
        @Nonnull
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(HorticultureItems.STRAWBERRY.get());
        }
    };

    public Horticulture() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        HorticultureBlocks.BLOCKS.register(eventBus);
        HorticultureItems.ITEMS.register(eventBus);
        HorticultureRegistries.SERIALIZERS.register(eventBus);
        HorticultureTileEntities.TILE_ENTITIES.register(eventBus);
        HorticultureWorld.FOLIAGE_PLACERS.register(eventBus);
        HorticultureWorld.LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

    @SubscribeEvent
    public static void onDataGathering(final GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new HorticultureLootTables(generator));
            BlockTagsProvider blockTags = new HorticultureBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new HorticultureItemTags(generator, blockTags, event.getExistingFileHelper()));
            generator.addProvider(new HorticultureRecipes(generator));
            generator.addProvider(new HorticultureDatabase(generator));
            generator.addProvider(new HorticultureBlockStates(generator, event.getExistingFileHelper()));
        }

        if (event.includeClient()) {
            generator.addProvider(new HorticultureLanguage(generator));
            generator.addProvider(new HorticultureItemModels(generator, event.getExistingFileHelper()));
        }
    }
}