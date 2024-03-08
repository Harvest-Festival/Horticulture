package uk.joshiejack.horticulture;

import net.minecraft.DetectedVersion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.data.*;
import uk.joshiejack.horticulture.world.HorticultureLevel;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;
import uk.joshiejack.horticulture.world.item.HorticultureItems;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;

import javax.annotation.Nonnull;
import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(Horticulture.MODID)
public class Horticulture {
    public static final String MODID = "horticulture";

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static class CreativeTab {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register(MODID, () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.horticulture"))
                .icon(HorticultureItems.STRAWBERRY::toStack)
                .displayItems((params, output) -> {
                    HorticultureItems.ITEMS.getEntries().stream()
                            .map(DeferredHolder::get)
                            .forEach(output::accept);
                }).build());

    }

    public Horticulture(IEventBus eventBus) {
        CreativeTab.CREATIVE_MODE_TABS.register(eventBus);
        HorticultureSounds.SOUNDS.register(eventBus);
        HorticultureBlocks.BLOCKS.register(eventBus);
        HorticultureItems.ITEMS.register(eventBus);
        HorticultureRecipes.RECIPE_TYPES.register(eventBus);
        HorticultureRecipes.SERIALIZERS.register(eventBus);
        HorticultureBlockEntities.BLOCK_ENTITIES.register(eventBus);
        HorticultureLevel.register(eventBus);
    }

    @SubscribeEvent
    public static void onDataGathering(GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final PackOutput output = event.getGenerator().getPackOutput();
        //Add the datapack entries
        //Client
        generator.addProvider(event.includeClient(), new HorticultureBlockStates(output, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new HorticultureItemModels(output, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new HorticultureLanguage(output));
        generator.addProvider(event.includeClient(), new HorticultureSpriteSourceProvider(output, event.getLookupProvider(), event.getExistingFileHelper()));

        //Server
        HorticultureBlockTags blocktags = new HorticultureBlockTags(output, event.getLookupProvider(), event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blocktags);
        generator.addProvider(event.includeServer(), new HorticultureDataProvider(output, event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new HorticultureLootTables(output));
        generator.addProvider(event.includeServer(), new HorticultureItemTags(output, event.getLookupProvider(), blocktags.contentsGetter(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new HorticultureRecipeProvider(output));

        generator.addProvider(event.includeServer(), new HorticultureDatabase(output));

        //PackMetadataGenerator
        generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Horticulture"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.SERVER_DATA),
                Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
    }

    public static class HorticultureSounds {
        public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MODID);
        public static final DeferredHolder<SoundEvent, SoundEvent> SEED_MAKER = createSoundEvent("seed_maker");

        @SuppressWarnings("all")
        private static DeferredHolder<SoundEvent, SoundEvent> createSoundEvent(@Nonnull String name) {
            return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, name)));
        }
    }
}