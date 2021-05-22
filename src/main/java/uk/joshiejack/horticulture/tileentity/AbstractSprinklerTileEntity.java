package uk.joshiejack.horticulture.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.item.HorticultureItems;
import uk.joshiejack.penguinlib.data.TimeUnitRegistry;
import uk.joshiejack.penguinlib.util.helpers.minecraft.FakePlayerHelper;
import uk.joshiejack.penguinlib.util.helpers.minecraft.TimeHelper;

public abstract class AbstractSprinklerTileEntity extends TileEntity implements ITickableTileEntity {
    public static final String START = Horticulture.MODID + ":sprinkler_start";
    public static final String FREQUENCY = Horticulture.MODID + ":sprinkler_frequency";
    public static final String FINISH = Horticulture.MODID + ":sprinkler_finish";
    private final double height;
    private final int range;

    @SuppressWarnings("WeakerAccess")
    public AbstractSprinklerTileEntity(TileEntityType<?> type, double height, int range) {
        super(type);
        this.height = height;
        this.range = range;
    }

    @Override
    public void tick() {
        assert level != null;
        if (level.getGameTime() % TimeUnitRegistry.get(FREQUENCY) == 1
                && TimeHelper.isBetween(level, (int) TimeUnitRegistry.get(START), (int) TimeUnitRegistry.get(FINISH))) {
            if (level.isClientSide) {
                int setting = (2 - Minecraft.getInstance().options.particles.getId());
                for (int i = 0; i < setting * 32; i++) {
                    double one = getRandomDouble();
                    double two = getRandomDouble();
                    level.addParticle(ParticleTypes.SPLASH, worldPosition.getX() + 0.5D, worldPosition.getY() + height, worldPosition.getZ() + 0.5D, one, 0D, two);
                    level.addParticle(ParticleTypes.SPLASH, worldPosition.getX() + 0.5D, worldPosition.getY() + height, worldPosition.getZ() + 0.5D, one - 0.05D, 0D, two - 0.05D);
                    level.addParticle(ParticleTypes.SPLASH, worldPosition.getX() + 0.5D, worldPosition.getY() + height, worldPosition.getZ() + 0.5D, one - 0.05D, 0D, two + 0.05D);
                    level.addParticle(ParticleTypes.SPLASH, worldPosition.getX() + 0.5D, worldPosition.getY() + height, worldPosition.getZ() + 0.5D, one + 0.05D, 0D, two - 0.05D);
                    level.addParticle(ParticleTypes.SPLASH, worldPosition.getX() + 0.5D, worldPosition.getY() + height, worldPosition.getZ() + 0.5D, one + 0.05D, 0D, two + 0.05D);
                }
            } else {
                boolean soundPlayed = false;
                for (BlockPos position : BlockPos.betweenClosed(worldPosition.offset(-range, -2, -range), worldPosition.offset(range, 0, range))) {
                    if (!position.equals(worldPosition)) {
                        if (level.dimensionType().ultraWarm()) {
                            int x = position.getX();
                            int y = position.getY();
                            int z = position.getZ();
                            if (!soundPlayed) {
                                level.playSound(null, position, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
                                soundPlayed = true;
                            }

                            for (int l = 0; l < 8; ++l) {
                                level.addParticle(ParticleTypes.LARGE_SMOKE, (double) x + Math.random(), (double) y + Math.random(), (double) z + Math.random(), 0.0D, 0.0D, 0.0D);
                            }
                        } else
                            HorticultureItems.WATERING_CAN.get().water(FakePlayerHelper.getFakePlayerWithPosition((ServerWorld) level, position), level, position, ItemStack.EMPTY, Hand.MAIN_HAND);
                    }
                }
            }
        }
    }

    protected abstract double getRandomDouble();
}
