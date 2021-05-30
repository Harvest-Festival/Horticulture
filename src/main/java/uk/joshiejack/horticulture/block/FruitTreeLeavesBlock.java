package uk.joshiejack.horticulture.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Random;

@SuppressWarnings("WeakerAccess, deprecation")
public class FruitTreeLeavesBlock extends LeavesBlock implements IGrowable {
    public static final BooleanProperty IN_SEASON = BooleanProperty.create("in_season");
    private final AbstractBlock.IExtendedPositionPredicate<RegistryObject<Block>> predicate;
    private final RegistryObject<Block> block;

    public FruitTreeLeavesBlock(RegistryObject<Block> fruit, AbstractBlock.IExtendedPositionPredicate<RegistryObject<Block>> function) {
        super(AbstractBlock.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS)
                .noOcclusion().isValidSpawn(FruitTreeLeavesBlock::ocelotOrParrot)
                .isSuffocating(FruitTreeLeavesBlock::never).isViewBlocking(FruitTreeLeavesBlock::never));
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, 7).setValue(PERSISTENT, Boolean.FALSE).setValue(IN_SEASON, Boolean.TRUE));
        this.block = fruit;
        this.predicate = function;
    }

    @Override
    public void randomTick(@Nonnull BlockState state, @Nonnull ServerWorld world, @Nonnull BlockPos pos, @Nonnull Random rand) {
        if (!state.getValue(PERSISTENT) && state.getValue(DISTANCE) == 7) {
            dropResources(state, world, pos);
            world.removeBlock(pos, false);
            world.updateNeighborsAt(pos, Blocks.AIR);
        } else if (state.getValue(IN_SEASON)) {
            if (ForgeHooks.onCropsGrowPre(world, pos, state, true)) {
                performBonemeal(world, rand, pos, state);
                ForgeHooks.onCropsGrowPost(world, pos, state);
            }
        }
    }

    @Override
    public boolean isValidBonemealTarget(@Nonnull IBlockReader worldIn, @Nonnull BlockPos pos, @Nonnull BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.below()).isAir(worldIn, pos);
    }

    @Override
    public boolean isBonemealSuccess(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        return rand.nextFloat() < 0.25F;
    }

    @Override
    public void performBonemeal(@Nonnull ServerWorld worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull BlockState state) {
        if (!state.getValue(BlockStateProperties.PERSISTENT) && predicate.test(state, worldIn, pos.below(), block)
                && worldIn.getBlockState(pos.below()).isAir()) {
            worldIn.setBlock(pos.below(), this.block.get().defaultBlockState(), 2);
        }
    }

    private static Boolean ocelotOrParrot(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    @SuppressWarnings("SameReturnValue")
    private static boolean never(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, PERSISTENT, IN_SEASON);
    }
}