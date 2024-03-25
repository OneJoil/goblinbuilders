package net.anessan.goblinbuilders.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class GoblinBuilderScreen extends AbstractContainerScreen<GoblinBuilderMenu> {
    private final static HashMap<String, Object> guistate = GoblinBuilderMenu.guistate;
    private static final ResourceLocation texture = new ResourceLocation("goblinbuilders:textures/screens/goblin_builder_gui.png");
    private static final Component FOLLOWME_BUTTON = Component.translatable("gui.goblinbuildersproject.goblin_builder_gui.button_follow_me");
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    private Button followMeButton;

    public GoblinBuilderScreen(GoblinBuilderMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.world = pMenu.world;
        this.x = pMenu.x;
        this.y = pMenu.y;
        this.z = pMenu.z;
        this.entity = pMenu.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    public void init() {
        super.init();
        this.followMeButton = addRenderableWidget(
            Button.builder(
                FOLLOWME_BUTTON,
                this::handleFollowMeButton)
                .bounds(this.leftPos + 8, this.topPos + 11, 65, 20)
                .build());
    }

    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    public void containerTick() {
        super.containerTick();
    }

    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    public void onClose() {
        super.onClose();
    }

    private void handleFollowMeButton(Button button) {

    }
}
