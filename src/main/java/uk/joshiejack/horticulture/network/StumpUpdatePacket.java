package uk.joshiejack.horticulture.network;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import uk.joshiejack.horticulture.world.block.entity.AbstractStumpBlockEntity;
import uk.joshiejack.penguinlib.PenguinLib;
import uk.joshiejack.penguinlib.network.packet.PenguinPacket;
import uk.joshiejack.penguinlib.util.registry.Packet;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@Packet(PacketFlow.CLIENTBOUND)
public record StumpUpdatePacket(BlockPos pos, Item item, int stage) implements PenguinPacket {
    public static final ResourceLocation ID = PenguinLib.prefix("stump_update");
    @Nonnull
    public ResourceLocation id() {
        return ID;
    }
    public StumpUpdatePacket(FriendlyByteBuf from) {
        this(from.readBlockPos(), from.readById(BuiltInRegistries.ITEM), from.readByte());
    }

    @Override
    public void write(FriendlyByteBuf to) {
        to.writeBlockPos(pos);
        to.writeId(BuiltInRegistries.ITEM, item);
        to.writeByte(stage);
    }

    @Override
    public void handle(Player player) {
        BlockEntity tile = player.level().getBlockEntity(pos);
        if (tile instanceof AbstractStumpBlockEntity)
            ((AbstractStumpBlockEntity<?>)tile).setMushroomData(item);
    }
}
