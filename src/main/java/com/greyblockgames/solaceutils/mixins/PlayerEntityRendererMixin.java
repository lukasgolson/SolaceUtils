package com.greyblockgames.solaceutils.mixins;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.Deadmau5FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.ShoulderParrotFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {


    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V", at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void mixin(EntityRenderDispatcher dispatcher, boolean bl, CallbackInfo ci) {
                


        ((PlayerEntityRendererInvoker) this).addFeature(new ShoulderParrotFeatureRenderer());


    }


}




