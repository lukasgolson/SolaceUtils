package com.greyblockgames.solaceutils.mixins.model;

import com.greyblockgames.solaceutils.Access.ArmorStandEntityModelAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ArmorStandModel.class)
public abstract class ArmorStandEntityModelMixin extends ArmorStandArmorModel implements ArmorStandEntityModelAccess {

    private ModelPart GBG_Cape = null;

    public ArmorStandEntityModelMixin(float f) {
        super(f);
    }


    @Inject(method = "<init>(F)V", at = @At(value = "RETURN"))
    public void mixin(float f, CallbackInfo ci) {
        this.GBG_Cape = new ModelPart(this, 0, 0);
        this.GBG_Cape.setTexSize(64, 32);
        this.GBG_Cape.addBox(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, f);
    }

    public void GBG_renderCape(PoseStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.GBG_Cape.render(matrices, vertices, light, overlay);
    }


}
