package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.Access.PlayerEntityAccess;
import com.greyblockgames.solaceutils.SolaceUtils;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityAccess {

    private UnusualEffectData GBG_unusualEffectData = null;
    private Boolean GBG_mouseEars = false;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public boolean GBG_hasUnusualEffect() {
        return (GBG_unusualEffectData != null);
    }

    @Override
    public UnusualEffectData GBG_getUnusualEffect() {
        return GBG_unusualEffectData;
    }

    @Override
    public boolean GBG_hasMouseEars() {
        return GBG_mouseEars;
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void injectIntoInit(World world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {

        if (SolaceUtils.cosmeticsData.unusualOwners.containsKey(getUuid().toString())) {
            GBG_unusualEffectData = SolaceUtils.cosmeticsData.unusualOwners.get(getUuid().toString());
        }

        if (SolaceUtils.cosmeticsData.EarOwners.contains(getUuid().toString())) {
            GBG_mouseEars = true;
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "tickMovement()V", at = @At(value = "RETURN"))
    public void tickMovement(CallbackInfo ci) {
        UnusualEffectData data = GBG_unusualEffectData;
        this.world.addParticle(data.getParticleEffect(), this.getParticleX(data.getRadius()), this.getBodyY(data.getHeightScale()), this.getParticleZ(data.getRadius()), data.getVelocity().x, data.getVelocity().y, data.getVelocity().z);

    }
}
