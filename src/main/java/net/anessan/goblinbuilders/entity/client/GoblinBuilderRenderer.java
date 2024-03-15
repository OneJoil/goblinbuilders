package net.anessan.goblinbuilders.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.anessan.goblinbuilders.GoblinBuilders;
import net.anessan.goblinbuilders.entity.custom.GoblinBuilderEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GoblinBuilderRenderer extends MobRenderer<GoblinBuilderEntity, GoblinBuilderModel<GoblinBuilderEntity>> {
    public GoblinBuilderRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new GoblinBuilderModel<>(pContext.bakeLayer(ModModelLayers.GOBLINBUILDER_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(GoblinBuilderEntity pEntity) {
        return new ResourceLocation(GoblinBuilders.MOD_ID, "textures/entity/goblinbuilder.png");
    }

    @Override
    public void render(GoblinBuilderEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
    if(pEntity.isBaby()) {
        pMatrixStack.scale(0.5f, 0.5f, 0.5f);
    }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
