package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;
import uk.joshiejack.horticulture.world.item.crafting.StumpRecipe;

public class StumpBlockEntity extends AbstractStumpBlockEntity<StumpRecipe> {
    public StumpBlockEntity(BlockPos pos, BlockState state) {
        super(HorticultureBlockEntities.OVERWORLD_STUMP.get(), pos, state, HorticultureRecipes.STUMP.get());
    }
}
