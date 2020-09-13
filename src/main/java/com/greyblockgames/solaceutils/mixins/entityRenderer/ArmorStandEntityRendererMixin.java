package com.greyblockgames.solaceutils.mixins.entityRenderer;

import com.greyblockgames.solaceutils.render.entity.feature.ArmorStandCapeFeatureRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public abstract class ArmorStandEntityRendererMixin extends LivingEntityRenderer<ArmorStandEntity, ArmorStandArmorEntityModel> {
    public ArmorStandEntityRendererMixin(EntityRenderDispatcher dispatcher, ArmorStandArmorEntityModel model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void mixin(EntityRenderDispatcher entityRenderDispatcher, CallbackInfo ci) {
        this.addFeature(new ArmorStandCapeFeatureRenderer(this));
    }

}
