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
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    Button button_follow_me, button_attack;
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

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void init() {
        super.init();
        button_follow_me = Button.builder(Component.translatable("gui.goblinbuildersproject.goblin_builder_gui.button_follow_me"), e -> {
        }).bounds(this.leftPos + 12, this.topPos + 11, 65, 20).build();
        guistate.put("button:button_follow_me", button_follow_me);
        this.addRenderableWidget(button_follow_me);
        button_attack = Button.builder(Component.translatable("gui.goblinbuildersproject.goblin_builder_gui.button_attack"), e -> {
        }).bounds(this.leftPos + 12, this.topPos + 39, 54, 20).build();
        guistate.put("button:button_attack", button_attack);
        this.addRenderableWidget(button_attack);
    }
}
