package uk.joshiejack.horticulture.world.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import uk.joshiejack.horticulture.Horticulture;
import uk.joshiejack.horticulture.world.item.crafting.HorticultureRecipes;
import uk.joshiejack.horticulture.world.item.crafting.SeedMakerRecipe;
import uk.joshiejack.penguinlib.world.block.entity.machine.MachineBlockEntity;
import uk.joshiejack.penguinlib.world.block.entity.machine.RecipeMachineBlockEntity;

public class SeedMakerBlockEntity extends RecipeMachineBlockEntity<SeedMakerRecipe> {
    public SeedMakerBlockEntity(BlockPos pos, BlockState state) {
        super(HorticultureBlockEntities.SEED_MAKER.get(), pos, state, HorticultureRecipes.SEED_MAKER.get());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SeedMakerBlockEntity entity) {
        MachineBlockEntity.serverTick(level, pos, state, entity);
        if (level.getGameTime() % 50L == 1L) {
            if (entity.isActive()) {
                double d0 = (double) pos.getX() + 0.5D;
                double d1 = (double) pos.getY() + 0.5D;
                double d2 = (double) pos.getZ() + 0.5D;
                level.playSound(null, d0, d1, d2, Horticulture.HorticultureSounds.SEED_MAKER.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            }
        }
    }
}