package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
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
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;
import uk.joshiejack.horticulture.world.block.entity.IronSprinklerBlockEntity;
import uk.joshiejack.horticulture.world.block.entity.OldSprinklerBlockEntity;
import uk.joshiejack.penguinlib.world.block.PenguinBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IronSprinklerBlock extends PenguinBlock implements EntityBlock {
    //Simple codec that just makes a new insance
    public static final MapCodec<Block> CODEC = MapCodec.unit(IronSprinklerBlock::new);
    private static final VoxelShape SHAPE = Shapes.create(0.2D, 0D, 0.2D, 0.8D, 0.7D, 0.8D);

    public IronSprinklerBlock() {
        super(Block.Properties.of().mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .strength(0.8F)
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
        return new IronSprinklerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ?
                createTickerHelper(type, HorticultureBlockEntities.IRON_SPRINKLER.get(),
                        (lvl, pos, st, entity) -> {
                            if (OldSprinklerBlockEntity.shouldTick(lvl))
                                OldSprinklerBlock.Client.tick(lvl, entity, pos);
                        }) :
                createTickerHelper(type, HorticultureBlockEntities.IRON_SPRINKLER.get(),
                        (lvl, pos, st, entity) -> {
                            if (OldSprinklerBlockEntity.shouldTick(lvl))
                                OldSprinklerBlockEntity.serverTick(lvl, pos, entity);
                        });
    }
}
