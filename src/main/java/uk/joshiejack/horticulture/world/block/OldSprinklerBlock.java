package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.entity.AbstractSprinklerBlockEntity;
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;
import uk.joshiejack.horticulture.world.block.entity.OldSprinklerBlockEntity;
import uk.joshiejack.penguinlib.world.block.PenguinBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class OldSprinklerBlock extends PenguinBlock implements EntityBlock {
    public static final MapCodec<Block> CODEC = MapCodec.unit(IronSprinklerBlock::new);
    private static final VoxelShape SHAPE = Shapes.create(0.2D, 0D, 0.2D, 0.8D, 0.5D, 0.8D);

    public OldSprinklerBlock() {
        super(Block.Properties.of().mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .strength(0.4F)
                .noCollission()
                .sound(SoundType.METAL));
    }

    @Override
    public @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter getter, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new OldSprinklerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ?
                createTickerHelper(type, HorticultureBlockEntities.OLD_SPRINKLER.get(),
                        (lvl, pos, st, entity) -> {
                            if (OldSprinklerBlockEntity.shouldTick(lvl))
                                Client.tick(lvl, entity, pos);
                        }) :
                createTickerHelper(type, HorticultureBlockEntities.OLD_SPRINKLER.get(),
                        (lvl, pos, st, entity) -> {
                            if (OldSprinklerBlockEntity.shouldTick(lvl))
                                OldSprinklerBlockEntity.serverTick(lvl, pos, entity);
                        });
    }

    public static class Client {
        public static void tick(Level level, AbstractSprinklerBlockEntity entity, BlockPos pos) {
            int setting = (2 - Minecraft.getInstance().options.particles().get().getId());
            for (int i = 0; i < setting * 32; i++) {
                double one = entity.getRandomDouble();
                double two = entity.getRandomDouble();
                level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5D, pos.getY() + entity.height, pos.getZ() + 0.5D, one, 0D, two);
                level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5D, pos.getY() + entity.height, pos.getZ() + 0.5D, one - 0.05D, 0D, two - 0.05D);
                level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5D, pos.getY() + entity.height, pos.getZ() + 0.5D, one - 0.05D, 0D, two + 0.05D);
                level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5D, pos.getY() + entity.height, pos.getZ() + 0.5D, one + 0.05D, 0D, two - 0.05D);
                level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5D, pos.getY() + entity.height, pos.getZ() + 0.5D, one + 0.05D, 0D, two + 0.05D);
            }
        }
    }
}
