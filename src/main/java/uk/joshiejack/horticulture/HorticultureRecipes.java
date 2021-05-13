package uk.joshiejack.horticulture;

import net.minecraftforge.fml.common.Mod;

import static uk.joshiejack.horticulture.Horticulture.MODID;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = MODID)
public class HorticultureRecipes {
//    @SubscribeEvent
//    public static void onDatabaseLoaded(DatabaseLoadedEvent event) {
//        event.table("stump").rows().forEach(row -> {
//            String item = row.get("item");
//            ItemStack stack = StackHelper.getStackFromString(item);
//            BlockState state = StateAdapter.fromString(row.get("state"));
//            StumpTileEntity.registry.register(Holder.getFromStack(stack), new StumpTileEntity.MushroomData(stack, state, row.get("limit"), row.getColor("color")));
//            StackHelper.registerSynonym(item + "_spores", StackHelper.withNBT(SPORES, stack.writeToNBT(new CompoundNBT())));
//        });
//    }
}
