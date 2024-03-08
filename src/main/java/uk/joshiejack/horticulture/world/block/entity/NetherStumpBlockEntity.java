package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;
import uk.joshiejack.horticulture.world.item.crafting.NetherStumpRecipe;

public class NetherStumpBlockEntity extends AbstractStumpBlockEntity<NetherStumpRecipe> {
    public NetherStumpBlockEntity(BlockPos pos, BlockState state) {
        super(HorticultureBlockEntities.NETHER_STUMP.get(), pos, state, HorticultureRecipes.NETHER_STUMP.get());
    }
}
