package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.Access.AbstractClientPlayerEntityAccess;
import com.greyblockgames.solaceutils.SolaceUtils;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity implements AbstractClientPlayerEntityAccess {

    private UnusualEffectData GBG_unusualEffectData = null;

    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }


    @Override
    public boolean GBG_hasUnusualEffect() {
        return (GBG_unusualEffectData != null);
    }

    @Override
    public UnusualEffectData GBG_getUnusualEffect() {
        return GBG_unusualEffectData;
    }




    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void injectIntoInit(ClientWorld world, GameProfile profile, CallbackInfo ci) {


        if (SolaceUtils.cosmeticsData.unusualOwners.containsKey(getUuid().toString())) {
            GBG_unusualEffectData = SolaceUtils.cosmeticsData.unusualOwners.get(getUuid().toString());
        }
    }


}
