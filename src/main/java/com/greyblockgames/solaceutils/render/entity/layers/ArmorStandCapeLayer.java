package com.greyblockgames.solaceutils.render.entity.layers;

import com.greyblockgames.solaceutils.Access.ArmorStandEntityModelAccess;
import com.greyblockgames.solaceutils.SolaceUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@Environment(EnvType.CLIENT)
public class ArmorStandCapeLayer extends RenderLayer<ArmorStand, ArmorStandArmorModel> {


    public ArmorStandCapeLayer(RenderLayerParent<ArmorStand, ArmorStandArmorModel> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ArmorStand entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (!entity.isInvisible() && entity.hasCustomName()) {

            ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.CHEST);
            if (itemStack.getItem() != Items.ELYTRA) {

                String[] entityName = entity.getCustomName().getContents().trim().split(":", 2);
                if (entityName[0].equals("SU")) {


                    if (SolaceUtils.cosmeticsData.capeList.containsKey(entityName[1])) {

                        ResourceLocation identifier = new ResourceLocation(SolaceUtils.MODID, "textures/capes/" + entityName[1] + ".png");

                        matrices.pushPose();
                        matrices.translate(0.0D, 0.0D, 0.125D);

                        final double d = 0;
                        final double e = 0;
                        final double m = 0;

                        float n = entity.yBodyRotO + (entity.yBodyRot - entity.yBodyRotO);
                        double o = Mth.sin(n * 0.017453292F);
                        double p = -Mth.cos(n * 0.017453292F);
                        float q = (float) e * 10.0F;
                        q = Mth.clamp(q, -6.0F, 32.0F);
                        float r = (float) (d * o + m * p) * 100.0F;
                        r = Mth.clamp(r, 0.0F, 150.0F);
                        float s = (float) (d * p - m * o) * 100.0F;
                        s = Mth.clamp(s, -20.0F, 20.0F);
                        if (r < 0.0F) {
                            r = 0.0F;
                        }

                        float t = Mth.lerp(tickDelta, entity.animationSpeedOld, entity.animationSpeed);
                        q += Mth.sin(Mth.lerp(tickDelta, entity.walkDistO, entity.walkDist) * 6.0F) * 32.0F * t;
                        if (entity.isCrouching()) {
                            q += 25.0F;
                        }


                        matrices.mulPose(Vector3f.XP.rotationDegrees(6.0F + r / 2.0F + q));
                        matrices.mulPose(Vector3f.ZP.rotationDegrees(s / 2.0F));
                        matrices.mulPose(Vector3f.YP.rotationDegrees(180.0F - s / 2.0F));
                        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityTranslucent(identifier));

                        ((ArmorStandEntityModelAccess) this.getParentModel()).GBG_renderCape(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
                        matrices.popPose();
                    }
                }
            }
        }
    }
}

