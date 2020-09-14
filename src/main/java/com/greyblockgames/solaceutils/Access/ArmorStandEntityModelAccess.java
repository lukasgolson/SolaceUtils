package com.greyblockgames.solaceutils.Access;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public interface ArmorStandEntityModelAccess {

    void GBG_renderCape(PoseStack matrices, VertexConsumer vertices, int light, int overlay);
}
