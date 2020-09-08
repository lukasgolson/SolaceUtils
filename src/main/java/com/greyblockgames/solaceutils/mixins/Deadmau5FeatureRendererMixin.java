package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.Access.PlayerEntityAccess;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.Deadmau5FeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Deadmau5FeatureRenderer.class)
public abstract class Deadmau5FeatureRendererMixin {


    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    public boolean isDeadmau5(String s, Object anObject) {
        return true;
    }

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    public void isCorrectPlayer(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AbstractClientPlayerEntity abstractClientPlayerEntity,
                                float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (!((PlayerEntityAccess) abstractClientPlayerEntity).GBG_hasMouseEars()) {
            ci.cancel();
        }
    }
}

