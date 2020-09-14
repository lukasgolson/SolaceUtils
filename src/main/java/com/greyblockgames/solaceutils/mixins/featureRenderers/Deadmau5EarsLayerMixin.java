package com.greyblockgames.solaceutils.mixins.featureRenderers;

import com.greyblockgames.solaceutils.Access.PlayerEntityAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.Deadmau5EarsLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Deadmau5EarsLayer.class)
public abstract class Deadmau5EarsLayerMixin {


    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"))
    public boolean isDeadmau5(String s, Object anObject) {
        return true;
    }

    @Inject(method = "render", at = @At(value = "HEAD"), cancellable = true)
    public void isCorrectPlayer(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, AbstractClientPlayer abstractClientPlayerEntity,
                                float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (!((PlayerEntityAccess) abstractClientPlayerEntity).GBG_hasMouseEars()) {
            ci.cancel();
        }
    }
}

