package uk.joshiejack.horticulture.world;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;

public class HorticultureWorld {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Horticulture.MODID);
//    public static final RegistryObject<GlobalLootModifierSerializer<?>> GRASS_SEED_DROPS = LOOT_MODIFIER_SERIALIZERS.register("grass_seed_drops", () -> {
//
//    });
    public static DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Horticulture.MODID);
}
