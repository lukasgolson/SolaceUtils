package com.greyblockgames.solaceutils.render.entity.feature;

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
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class ArmorStandCapeFeatureRenderer extends FeatureRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {


    public ArmorStandCapeFeatureRenderer(FeatureRendererContext<ArmorStandEntity, ArmorStandArmorEntityModel> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ArmorStandEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!entity.isInvisible()) {
            ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.CHEST);
            if (itemStack.getItem() != Items.ELYTRA) {
                matrices.push();
                matrices.translate(0.0D, 0.0D, 0.125D);

                double d = 0;
                double e = 0;
                double m = 0;


                float n = entity.prevBodyYaw + (entity.bodyYaw - entity.prevBodyYaw);
                double o = (double) MathHelper.sin(n * 0.017453292F);
                double p = (double) (-MathHelper.cos(n * 0.017453292F));
                float q = (float) e * 10.0F;
                q = MathHelper.clamp(q, -6.0F, 32.0F);
                float r = (float) (d * o + m * p) * 100.0F;
                r = MathHelper.clamp(r, 0.0F, 150.0F);
                float s = (float) (d * p - m * o) * 100.0F;
                s = MathHelper.clamp(s, -20.0F, 20.0F);
                if (r < 0.0F) {
                    r = 0.0F;
                }

                float t = MathHelper.lerp(tickDelta, entity.lastLimbDistance, entity.limbDistance);
                q += MathHelper.sin(MathHelper.lerp(tickDelta, entity.prevHorizontalSpeed, entity.horizontalSpeed) * 6.0F) * 32.0F * t;
                if (entity.isInSneakingPose()) {
                    q += 25.0F;
                }


                matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(6.0F + r / 2.0F + q));
                matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(s / 2.0F));
                matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F - s / 2.0F));
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(new Identifier(SolaceUtils.MODID, "textures/capes/admin.png")));

                ((ArmorStandEntityModelAccess) ((ArmorStandEntityModel) this.getContextModel())).GBG_renderCape(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                matrices.pop();
            }
        }
    }
}
