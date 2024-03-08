package uk.joshiejack.horticulture.world.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class CropBlock extends net.minecraft.world.level.block.CropBlock {
    public static final MapCodec<CropBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BlockBehaviour.Properties.CODEC.fieldOf("properties").forGetter(block -> block.properties),
            Codec.INT.fieldOf("max_age").forGetter(block -> block.maxAge),
            Codec.INT.fieldOf("regrow").forGetter(block -> block.regrow),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter((CropBlock block) -> block.supplier.get().asItem())
    ).apply(instance, (properties, age, regrow, seeds) -> new CropBlock(properties, age, regrow, () -> seeds)));


    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);
    private static IntegerProperty temp;
    private final IntegerProperty property;
    public final int maxAge;
    public final int regrow;
    public final Supplier<ItemLike> supplier;

    public CropBlock(int num, Supplier<ItemLike> supplier) {
        this(num, -1, supplier);
    }

    public CropBlock(int num, int regrow, Supplier<ItemLike> supplier) {
        this(Block.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP), num, regrow, supplier);
    }

    public CropBlock(Block.Properties properties, int num, int regrow, Supplier<ItemLike> supplier) {
        super(init(properties, num));
        this.maxAge = num - 1;
        this.regrow = regrow;
        this.property = temp;
        this.supplier = supplier;
    }

    @Override
    public MapCodec<? extends net.minecraft.world.level.block.CropBlock> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    protected ItemLike getBaseSeedId() {
        return supplier.get();
    }

    private static Block.Properties init(Block.Properties properties, int num) {
        temp = num == 3 ? BlockStateProperties.AGE_2
                : num == 4 ? BlockStateProperties.AGE_3
                : num == 5 ? AGE_4
                : AGE;
        return properties;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Nonnull
    @Override
    public IntegerProperty getAgeProperty() {
        return property == null ? temp : property;
    }

    @SuppressWarnings("deprecation")
    @Nonnull
    @Override
    public @NotNull InteractionResult use(@Nonnull BlockState state, @NotNull Level world, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult blockRayTraceResult) {
        int age = getAge(state);
        if (age >= getMaxAge()) {
            dropResources(state, world, pos);
            if (regrow == -1)
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            else
                world.setBlock(pos, getStateForAge(regrow), 2);
            return InteractionResult.SUCCESS;
        } else return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(@Nonnull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }
}
