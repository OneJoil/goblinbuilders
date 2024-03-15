package net.anessan.goblinbuilders.event;

import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.entity.client.GoblinBuilderModel;
import net.anessan.goblinbuilders.entity.client.ModModelLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoblinBuilders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventsBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.GOBLINBUILDER_LAYER, GoblinBuilderModel::createBodyLayer);
    }
}
