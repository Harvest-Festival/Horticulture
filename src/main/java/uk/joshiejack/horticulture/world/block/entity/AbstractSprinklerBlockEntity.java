package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.item.HorticultureItems;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.util.helper.FakePlayerHelper;
import uk.joshiejack.penguinlib.util.helper.TimeHelper;

public abstract class AbstractSprinklerBlockEntity extends BlockEntity {
    public static final TagKey<Block> SPRINKLER_GROWABLE = BlockTags.create(new ResourceLocation(Horticulture.MODID, "sprinkler_growable"));
    public static final String START = Horticulture.MODID + ":sprinkler_start";
    public static final String FREQUENCY = Horticulture.MODID + ":sprinkler_frequency";
    public static final String FINISH = Horticulture.MODID + ":sprinkler_finish";
    public final double height;
    private final int range;

    @SuppressWarnings("WeakerAccess")
    public AbstractSprinklerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, double height, int range) {
        super(type, pos, state);
        this.height = height;
        this.range = range;
    }


    public static boolean shouldTick(Level level) {
        return level.getGameTime() % TimeUnitRegistry.get(FREQUENCY) == 1
                && TimeHelper.isBetween(level, (int) TimeUnitRegistry.get(START), (int) TimeUnitRegistry.get(FINISH));
    }

    public static void serverTick(Level level, BlockPos pos, AbstractSprinklerBlockEntity entity) {
        boolean soundPlayed = false;
        for (BlockPos position : BlockPos.betweenClosed(pos.offset(-entity.range, -2, -entity.range), pos.offset(entity.range, 0, entity.range))) {
            if (!position.equals(pos)) {
                if (level.dimensionType().ultraWarm()) {
                    int x = position.getX();
                    int y = position.getY();
                    int z = position.getZ();
                    if (!soundPlayed) {
                        level.playSound(null, position, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
                        soundPlayed = true;
                    }

                    for (int l = 0; l < 8; ++l) {
                        level.addParticle(ParticleTypes.LARGE_SMOKE, (double) x + Math.random(), (double) y + Math.random(), (double) z + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    BlockState blockState = level.getBlockState(position);
                    if (blockState.is(SPRINKLER_GROWABLE) || blockState.is(BlockTags.CROPS) || blockState.is(BlockTags.SAPLINGS) || blockState.hasProperty(BlockStateProperties.MOISTURE))
                        HorticultureItems.WATERING_CAN.get().water(FakePlayerHelper.getFakePlayerWithPosition((ServerLevel) level, position), level, position, ItemStack.EMPTY, InteractionHand.MAIN_HAND);
                }
            }
        }
    }

    public abstract double getRandomDouble();
}
