package net.anessan.goblinbuilders;

import com.mojang.logging.LogUtils;
import net.anessan.goblinbuilders.entity.ModEntities;
import net.anessan.goblinbuilders.entity.client.GoblinBuilderRenderer;
import net.anessan.goblinbuilders.screen.GoblinBuilderScreen;
import net.anessan.goblinbuilders.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(GoblinBuilders.MOD_ID)
public class GoblinBuilders
{
    public static final String MOD_ID = "goblinbuilders";
    public static final Logger LOGGER = LogUtils.getLogger();
    public GoblinBuilders()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ModEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);


        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.GOBLINBUILDER.get(), GoblinBuilderRenderer::new);

            MenuScreens.register(ModMenuTypes.GOBLIN_BUILDER_GUI.get(), GoblinBuilderScreen::new);
        }
    }
}
