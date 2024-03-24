package net.anessan.goblinbuilders.screen;

import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.screen.buttons.FollowMeButton;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoblinBuilderGuiButtonMessage {
    private final int buttonID, x, y, z;

    public GoblinBuilderGuiButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public GoblinBuilderGuiButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(GoblinBuilderGuiButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(GoblinBuilderGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            Player entity = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            handleFollowMe(entity, buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleFollowMe(Player entity, int buttonID, int x, int y, int z) {
        HashMap guistate = GoblinBuilderMenu.guistate;
        if (buttonID == 0) {
            FollowMeButton.execute(entity);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        GoblinBuilders.addNetworkMessage(GoblinBuilderGuiButtonMessage.class, GoblinBuilderGuiButtonMessage::buffer, GoblinBuilderGuiButtonMessage::new, GoblinBuilderGuiButtonMessage::handler);
    }
}
