package uk.joshiejack.horticulture.tile;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.block.HorticultureBlocks;

@SuppressWarnings("ConstantConditions")
public class HorticultureTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Horticulture.MODID);
    public static final RegistryObject<TileEntityType<SeedMakerTileEntity>> SEED_MAKER = TILE_ENTITIES.register("seed_maker",
            () -> TileEntityType.Builder.of(SeedMakerTileEntity::new, HorticultureBlocks.SEED_MAKER.get()).build(null));
    public static final RegistryObject<TileEntityType<OldSprinklerTileEntity>> OLD_SPRINKLER = TILE_ENTITIES.register("old_sprinkler",
            () -> TileEntityType.Builder.of(OldSprinklerTileEntity::new, HorticultureBlocks.OLD_SPRINKLER.get()).build(null));
    public static final RegistryObject<TileEntityType<IronSprinklerTileEntity>> IRON_SPRINKLER = TILE_ENTITIES.register("iron_sprinkler",
            () -> TileEntityType.Builder.of(IronSprinklerTileEntity::new, HorticultureBlocks.IRON_SPRINKLER.get()).build(null));
    public static final RegistryObject<TileEntityType<StumpTileEntity>> OVERWORLD_STUMP = TILE_ENTITIES.register("overworld_stump",
            () -> TileEntityType.Builder.of(StumpTileEntity::new, HorticultureBlocks.OAK_STUMP.get(), HorticultureBlocks.SPRUCE_STUMP.get(),
                    HorticultureBlocks.BIRCH_STUMP.get(), HorticultureBlocks.JUNGLE_STUMP.get(), HorticultureBlocks.ACACIA_STUMP.get(),
                    HorticultureBlocks.DARK_OAK_STUMP.get()).build(null));
    public static final RegistryObject<TileEntityType<NetherStumpTileEntity>> NETHER_STUMP = TILE_ENTITIES.register("nether_stump",
            () -> TileEntityType.Builder.of(NetherStumpTileEntity::new, HorticultureBlocks.CRIMSON_STUMP.get(), HorticultureBlocks.WARPED_STUMP.get()).build(null));
}
