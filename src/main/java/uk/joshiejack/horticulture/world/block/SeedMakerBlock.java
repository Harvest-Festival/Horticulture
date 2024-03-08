package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
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
import org.jetbrains.annotations.NotNull;
import uk.joshiejack.horticulture.world.block.entity.HorticultureBlockEntities;
import uk.joshiejack.horticulture.world.block.entity.SeedMakerBlockEntity;
import uk.joshiejack.penguinlib.world.block.RotatableBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SeedMakerBlock extends RotatableBlock implements EntityBlock {
    public static final MapCodec<Block> CODEC = MapCodec.unit(SeedMakerBlock::new);

    public SeedMakerBlock() {
        super(Block.Properties.of().mapColor(MapColor.METAL)
                .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                .strength(2.0F)
                .noCollission()
                .noOcclusion()
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL));
        hasInventory = true;
    }

    @Override
    public @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
        return new SeedMakerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, HorticultureBlockEntities.SEED_MAKER.get(), SeedMakerBlockEntity::serverTick);
    }
}
