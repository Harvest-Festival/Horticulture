package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class TropicalFruitTreeLeavesBlock extends AbstractFruitTreeLeavesBlock {
    public static final MapCodec<TropicalFruitTreeLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("fruit").forGetter((block) -> block.fruit.get())
    ).apply(instance, (fruit) -> new TropicalFruitTreeLeavesBlock(() -> fruit)));
    public TropicalFruitTreeLeavesBlock(Supplier<Block> fruit) {
        super(fruit, TropicalFruitTreeLeavesBlock::canPlaceTemperateFruit);
    }

    @Override
    public @NotNull MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    private static Boolean canPlaceTemperateFruit(BlockState state, BlockGetter reader, BlockPos pos, Supplier<Block> block) {
        return BlockPos.betweenClosedStream(pos.offset(-1, 0, -1), pos.offset(1, 0, 1))
                .noneMatch(target -> reader.getBlockState(target).getBlock() == block.get());
    }
}
