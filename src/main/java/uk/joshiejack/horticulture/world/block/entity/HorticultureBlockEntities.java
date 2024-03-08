package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.block.HorticultureBlocks;

@SuppressWarnings("ConstantConditions")
public class HorticultureBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Horticulture.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SeedMakerBlockEntity>> SEED_MAKER = BLOCK_ENTITIES.register("seed_maker",
            () -> BlockEntityType.Builder.of(SeedMakerBlockEntity::new, HorticultureBlocks.SEED_MAKER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OldSprinklerBlockEntity>> OLD_SPRINKLER = BLOCK_ENTITIES.register("old_sprinkler",
            () -> BlockEntityType.Builder.of(OldSprinklerBlockEntity::new, HorticultureBlocks.OLD_SPRINKLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<IronSprinklerBlockEntity>> IRON_SPRINKLER = BLOCK_ENTITIES.register("iron_sprinkler",
            () -> BlockEntityType.Builder.of(IronSprinklerBlockEntity::new, HorticultureBlocks.IRON_SPRINKLER.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StumpBlockEntity>> OVERWORLD_STUMP = BLOCK_ENTITIES.register("overworld_stump",
            () -> BlockEntityType.Builder.of(StumpBlockEntity::new, HorticultureBlocks.OAK_STUMP.get(), HorticultureBlocks.SPRUCE_STUMP.get(),
                    HorticultureBlocks.BIRCH_STUMP.get(), HorticultureBlocks.JUNGLE_STUMP.get(), HorticultureBlocks.ACACIA_STUMP.get(),
                    HorticultureBlocks.DARK_OAK_STUMP.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NetherStumpBlockEntity>> NETHER_STUMP = BLOCK_ENTITIES.register("nether_stump",
            () -> BlockEntityType.Builder.of(NetherStumpBlockEntity::new, HorticultureBlocks.CRIMSON_STUMP.get(), HorticultureBlocks.WARPED_STUMP.get()).build(null));
}
