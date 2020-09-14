package com.greyblockgames.solaceutils.mixins.entityRenderer;

import com.greyblockgames.solaceutils.render.entity.layers.ArmorStandCapeLayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandRenderer.class)
public abstract class ArmorStandRendererMixin extends LivingEntityRenderer<ArmorStand, ArmorStandArmorModel> {
    public ArmorStandRendererMixin(EntityRenderDispatcher dispatcher, ArmorStandArmorModel model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void mixin(EntityRenderDispatcher entityRenderDispatcher, CallbackInfo ci) {
        this.addLayer(new ArmorStandCapeLayer(this));
    }

}
