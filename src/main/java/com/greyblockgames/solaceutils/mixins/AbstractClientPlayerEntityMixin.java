package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.SolaceUtils;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    private UnusualEffectData unusualEffectData = null;

    public AbstractClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }


    public boolean hasUnusualEffect() {
        return (unusualEffectData != null);
    }

    public UnusualEffectData getUnusualEffect() {
        return unusualEffectData;
    }

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void injectIntoInit(CallbackInfo ci) {
        if (SolaceUtils.cosmeticsData.unusualOwners.containsKey(super.getUuid().toString())) {
            unusualEffectData = SolaceUtils.cosmeticsData.unusualOwners.get(super.uuid.toString());
        }
    }


}
