package com.greyblockgames.solaceutils.render.entity.feature;


import com.greyblockgames.solaceutils.Access.PlayerEntityAccess;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class PlayerUnusualParticlesFeature extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    int counter = 60;
    int counterRequirement = counter;
    boolean counterSet = false;

    public PlayerUnusualParticlesFeature(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {


        if (((PlayerEntityAccess) entity).GBG_hasUnusualEffect()) {
            counter++;
            if (counter >= counterRequirement) {
                counter = 0;
                UnusualEffectData data = ((PlayerEntityAccess) entity).GBG_getUnusualEffect();

                entity.clientWorld.addParticle(data.getParticleEffect(), entity.getParticleX(data.getRadius()), entity.getBodyY(data.getHeightScale()), entity.getParticleZ(data.getRadius()), data.getVelocity().x, data.getVelocity().y, data.getVelocity().z);

                if (!counterSet) {
                    counterRequirement = data.getSpawnRate();
                    counterSet = true;
                }
            }
        }


    }
}

