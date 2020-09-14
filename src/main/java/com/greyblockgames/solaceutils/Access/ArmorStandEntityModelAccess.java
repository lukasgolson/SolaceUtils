package com.greyblockgames.solaceutils.Access;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface ArmorStandEntityModelAccess {

    void GBG_renderCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay);
}
