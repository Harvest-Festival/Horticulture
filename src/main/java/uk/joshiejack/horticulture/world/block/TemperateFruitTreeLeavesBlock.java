package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class TemperateFruitTreeLeavesBlock extends AbstractFruitTreeLeavesBlock {
    public static final MapCodec<TemperateFruitTreeLeavesBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("fruit").forGetter((block) -> block.fruit.get())
    ).apply(instance, (fruit) -> new TemperateFruitTreeLeavesBlock(() -> fruit)));
    public TemperateFruitTreeLeavesBlock(Supplier<Block> fruit) {
        super(fruit, TemperateFruitTreeLeavesBlock::canPlaceTropicalFruit);
    }

    @Override
    public @NotNull MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    private static Boolean canPlaceTropicalFruit(BlockState state, BlockGetter reader, BlockPos pos, Supplier<Block> block) {
        for (Direction facing: Direction.Plane.HORIZONTAL) {
            if (reader.getBlockState(pos.relative(facing)).is(BlockTags.LOGS)) return true;
        }

        return false;
    }
}
