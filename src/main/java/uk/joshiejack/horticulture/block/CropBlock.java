package uk.joshiejack.horticulture.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class CropBlock extends CropsBlock {
    public static final IntegerProperty AGE_4 = IntegerProperty.create("age", 0, 4);
    private static IntegerProperty temp;
    private final IntegerProperty property;
    private final int maxAge;
    private final int regrow;

    public CropBlock(int num) {
        this(num, -1);
    }

    public CropBlock(int num, int regrow) {
        this(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP), num, regrow);
    }

    public CropBlock(AbstractBlock.Properties properties, int num, int regrow) {
        super(init(properties, num));
        this.maxAge = num - 1;
        this.regrow = regrow;
        this.property = temp;
    }

    private static AbstractBlock.Properties init(AbstractBlock.Properties properties, int num) {
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
    public ActionResultType use(@Nonnull BlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull PlayerEntity player,
                                @Nonnull Hand hand, @Nonnull BlockRayTraceResult blockRayTraceResult) {
        int age = getAge(state);
        if (age >= getMaxAge()) {
            dropResources(state, world, pos);
            if (regrow == -1)
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            else
                world.setBlock(pos, getStateForAge(regrow), 2);
            return ActionResultType.SUCCESS;
        } else return ActionResultType.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }
}
