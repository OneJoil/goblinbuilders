package net.anessan.goblinbuilders.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.entity.custom.GoblinBuilderEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GoblinBuilderScreen extends AbstractContainerScreen<GoblinBuilderMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("goblinbuildersproject:textures/screens/goblin_builder_gui.png");
    private static final Component FOLLOWME_BUTTON = Component.translatable("gui.goblinbuildersproject.goblin_builder_gui.button_follow_me");
    private final GoblinBuilderEntity goblinBuilder;
    Button button;

    public GoblinBuilderScreen(GoblinBuilderMenu pMenu, Inventory pPlayerInventory, GoblinBuilderEntity pGoblinbuilder) {
        super(pMenu, pPlayerInventory, pGoblinbuilder.getDisplayName());
        this.goblinBuilder = pGoblinbuilder;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        pGuiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    public void init() {
        super.init();
        this.button = addRenderableWidget(
            Button.builder(
                FOLLOWME_BUTTON,
                this::handleFollowMeButton)
                .bounds(this.leftPos + 8, this.topPos + 11, 65, 20)
                .build());
    }

    private void handleFollowMeButton(Button pButton) {
        GoblinBuilders.PACKET_HANDLER.sendToServer(new GoblinBuilderGuiButtonMessage(0, x, y, z));
        GoblinBuilderGuiButtonMessage.handleFollowMeButton(entity, 0, x, y, z);
    }
}
