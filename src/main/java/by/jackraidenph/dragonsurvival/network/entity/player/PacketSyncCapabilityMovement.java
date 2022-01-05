package by.jackraidenph.dragonsurvival.network.entity.player;

import by.jackraidenph.dragonsurvival.common.capability.DragonStateProvider;
import by.jackraidenph.dragonsurvival.network.IMessage;
import by.jackraidenph.dragonsurvival.network.NetworkHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class PacketSyncCapabilityMovement implements IMessage<PacketSyncCapabilityMovement>
{

    public int playerId;
    public double bodyYaw;
    public double headYaw;
    public double headPitch;
    public boolean bite;

    public PacketSyncCapabilityMovement() {
    }

    public PacketSyncCapabilityMovement(int playerId, double bodyYaw, double headYaw, double headPitch, boolean bite) {
        this.bodyYaw = bodyYaw;
        this.headYaw = headYaw;
        this.headPitch = headPitch;
        this.playerId = playerId;
        this.bite = bite;
    }

    @Override
    public void encode(PacketSyncCapabilityMovement m, PacketBuffer b) {
        b.writeInt(m.playerId);
        b.writeDouble(m.bodyYaw);
        b.writeDouble(m.headYaw);
        b.writeDouble(m.headPitch);
        b.writeBoolean(m.bite);
    }

    @Override
    public PacketSyncCapabilityMovement decode(PacketBuffer b) {
        return new PacketSyncCapabilityMovement(b.readInt(), b.readDouble(), b.readDouble(), b.readDouble(), b.readBoolean()
        );
    }

    private void writeVec3d(PacketBuffer buffer, Vector3d vec) {
        buffer.writeDouble(vec.x);
        buffer.writeDouble(vec.y);
        buffer.writeDouble(vec.z);
    }

    private Vector3d readVec3d(PacketBuffer buffer) {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new Vector3d(x, y, z);
    }

    @Override
    public void handle(PacketSyncCapabilityMovement syncCapabilityMovement, Supplier<NetworkEvent.Context> supplier) { // TODO Clean this up
    	NetworkEvent.Context context = supplier.get();
    	ServerPlayerEntity player = context.getSender();
    	if (player == null) {
		    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> (SafeRunnable)() -> runClient(syncCapabilityMovement, supplier));
    		return;
    	}
    	Entity entity = player.level.getEntity(syncCapabilityMovement.playerId);
    	if (entity == null || entity.level == null) {
    		context.setPacketHandled(true);
    		return;
    	}
    	if (entity instanceof PlayerEntity) {
            DragonStateProvider.getCap(entity).ifPresent(dragonStateHandler -> {
                dragonStateHandler.setMovementData(syncCapabilityMovement.bodyYaw, syncCapabilityMovement.headYaw, syncCapabilityMovement.headPitch, syncCapabilityMovement.bite);
            });
        }
    	if (!entity.level.isClientSide) {
		    NetworkHandler.CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), syncCapabilityMovement);
	    }
    	context.setPacketHandled(true);
    }
	
	@OnlyIn(Dist.CLIENT)
	public void runClient(PacketSyncCapabilityMovement message, Supplier<NetworkEvent.Context> supplier){
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			PlayerEntity thisPlayer = Minecraft.getInstance().player;
			if (thisPlayer != null) {
				World world = thisPlayer.level;
				Entity entity = world.getEntity(message.playerId);
				if (entity instanceof PlayerEntity) {
					DragonStateProvider.getCap(entity).ifPresent(dragonStateHandler -> {
						if (entity == thisPlayer)
							dragonStateHandler.setMovementData(message.bodyYaw, ((PlayerEntity) entity).yHeadRot, entity.xRot, message.bite);
						else
							dragonStateHandler.setMovementData(message.bodyYaw, message.headYaw, message.headPitch, message.bite);
					});
				}
			}
			context.setPacketHandled(true);
		});
	}
}