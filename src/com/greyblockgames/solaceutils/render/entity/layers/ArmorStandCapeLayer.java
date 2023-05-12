package com.greyblockgames.solaceutils.render.entity.layers;

import com.greyblockgames.solaceutils.Access.ArmorStandEntityModelAccess;
import com.greyblockgames.solaceutils.SolaceUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class ArmorStandCapeLayer extends FeatureRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {


    public ArmorStandCapeLayer(FeatureRendererContext<ArmorStandEntity, ArmorStandArmorEntityModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorStandEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (!entity.isInvisible() && entity.hasCustomName()) {

            ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
            if (itemStack.getItem() != Items.ELYTRA) {

                String[] entityName = entity.getCustomName().asString().trim().split(":", 2);
                if (entityName[0].equals("SU")) {


                    if (SolaceUtils.cosmeticsData.capeList.containsKey(entityName[1])) {

                        Identifier identifier = new Identifier(SolaceUtils.MODID, "textures/capes/" + entityName[1] + ".png");

                        matrices.push();
                        matrices.translate(0.0D, 0.0D, 0.125D);


                        float n = entity.prevBodyYaw + (entity.bodyYaw - entity.prevBodyYaw);
                        double o = MathHelper.sin(n * 0.017453292F);
                        double p = -MathHelper.cos(n * 0.017453292F);
                        float q = (float) 0 * 10.0F;
                        q = MathHelper.clamp(q, -6.0F, 32.0F);
                        float r = (float) (0 * o + 0 * p) * 100.0F;
                        r = MathHelper.clamp(r, 0.0F, 150.0F);
                        float s = (float) (0 * p - 0 * o) * 100.0F;
                        s = MathHelper.clamp(s, -20.0F, 20.0F);
                        if (r < 0.0F) {
                            r = 0.0F;
                        }

                        float t = MathHelper.lerp(tickDelta, entity.lastLimbDistance, entity.limbDistance);
                        q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * t;
                        if (entity.isInSneakingPose()) {
                            q += 25.0F;
                        }


                        matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
                        matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
                        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
                        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(identifier));

                        ((ArmorStandEntityModelAccess) this.getContextModel()).GBG_renderCape(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                        matrices.pop();
                    }
                }
            }
        }
    }
}

