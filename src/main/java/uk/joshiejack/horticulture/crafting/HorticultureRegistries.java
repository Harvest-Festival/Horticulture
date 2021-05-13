package uk.joshiejack.horticulture.crafting;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.penguinlib.item.crafting.SimplePenguinRecipe;

@Mod.EventBusSubscriber(modid = Horticulture.MODID)
public class HorticultureRegistries {
    public static final DeferredRegister<IRecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Horticulture.MODID);
    public static final RegistryObject<IRecipeSerializer<SeedMakerRecipe>> SEED_MAKER_SERIALIZER = SERIALIZERS.register("seed_maker", () -> new SimplePenguinRecipe.Serializer<>(SeedMakerRecipe::new));
    public static final RegistryObject<StumpRecipeSerializer> STUMP_SERIALIZER = SERIALIZERS.register("stump", () -> new StumpRecipeSerializer(StumpRecipe::new));
    public static final RegistryObject<StumpRecipeSerializer> NETHER_STUMP_SERIALIZER = SERIALIZERS.register("nether_stump", () -> new StumpRecipeSerializer(NetherStumpRecipe::new));
    public static final IRecipeType<SeedMakerRecipe> SEED_MAKER = IRecipeType.register(Horticulture.MODID + ":seed_maker");
    public static final IRecipeType<StumpRecipe> STUMP = IRecipeType.register(Horticulture.MODID + ":stump");
    public static final IRecipeType<NetherStumpRecipe> NETHER_STUMP = IRecipeType.register(Horticulture.MODID + ":nether_stump");
}