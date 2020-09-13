package com.greyblockgames.solaceutils.mixins.model;

import com.greyblockgames.solaceutils.Access.ArmorStandEntityModelAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ArmorStandEntityModel.class)
public abstract class ArmorStandEntityModelMixin extends ArmorStandArmorEntityModel implements ArmorStandEntityModelAccess {

    private ModelPart GBG_Cape = null;

    public ArmorStandEntityModelMixin(float f) {
        super(f);
    }




    @Inject(method = "<init>(F)V",  at = @At(value = "RETURN"))
    public void mixin(float f, CallbackInfo ci) {
        this.GBG_Cape = new ModelPart(this, 0, 0);
        this.GBG_Cape.setTextureSize(64, 32);
        this.GBG_Cape.addCuboid(-5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, f);
    }

    @Inject(method = "getBodyParts()Ljava/lang/Iterable;",  at = @At(value = "RETURN"))
   public void getBodyPartsMixin(CallbackInfoReturnable<Iterable<ModelPart>> cir) {
    
    }


    public void GBG_renderCape(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        this.GBG_Cape.render(matrices, vertices, light, overlay);
    }


}
