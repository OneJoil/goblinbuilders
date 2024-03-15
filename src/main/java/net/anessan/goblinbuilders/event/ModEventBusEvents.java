package net.anessan.goblinbuilders.event;

import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.entity.ModEntities;
import net.anessan.goblinbuilders.entity.custom.GoblinBuilderEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GoblinBuilders.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GOBLINBUILDER.get(), GoblinBuilderEntity.createAttributes().build());
    }
}
