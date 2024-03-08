package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.joshiejack.horticulture.world.block.entity.NetherStumpBlockEntity;

public class NetherStumpBlock extends AbstractStumpBlock {
    public static final MapCodec<Block> CODEC = simpleCodec(NetherStumpBlock::new);
    public NetherStumpBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new NetherStumpBlockEntity(pos, state);
    }
}
