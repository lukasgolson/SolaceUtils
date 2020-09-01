package com.greyblockgames.solaceutils.render.entity.feature;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.particle.ParticleTypes;

@Environment(EnvType.CLIENT)
public class PlayerUnusualParticlesFeature extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>{

    int counter = 60;

    public PlayerUnusualParticlesFeature(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        counter++;
        if (counter>=10) {
         counter = 0;
            entity.world.addParticle(ParticleTypes.DRIPPING_HONEY, entity.getParticleX(0.55D), entity.getBodyY(0.4), entity.getParticleZ(0.55D), 0.2D, 0.0D, 0.0D);
        }
    }
}
