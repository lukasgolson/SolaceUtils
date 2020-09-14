package com.greyblockgames.solaceutils.mixins.entities;

import com.greyblockgames.solaceutils.Access.PlayerEntityAccess;
import com.greyblockgames.solaceutils.SolaceUtils;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityAccess {

    @Unique
    int counter = 60;
    @Unique
    int counterRequirement = counter;
    @Unique
    boolean counterSet = false;
    private UnusualEffectData GBG_unusualEffectData = null;
    private Boolean GBG_mouseEars = false;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
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
    private void injectIntoInit(Level world, BlockPos pos, float yaw, GameProfile profile, CallbackInfo ci) {

        if (SolaceUtils.cosmeticsData.unusualOwners.containsKey(getUUID().toString())) {
            GBG_unusualEffectData = SolaceUtils.cosmeticsData.unusualOwners.get(getUUID().toString());
        }

        if (SolaceUtils.cosmeticsData.EarOwners.contains(getUUID().toString())) {
            GBG_mouseEars = true;
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "aiStep()V", at = @At(value = "RETURN"))
    public void tickMovement(CallbackInfo ci) {

        if (GBG_unusualEffectData != null) {
            counter++;

            if (counter >= counterRequirement) {
                counter = 0;
                UnusualEffectData data = GBG_unusualEffectData;

                this.level.addParticle(data.getParticleEffect(), this.getRandomX(data.getRadius()), this.getY(data.getHeightScale()), this.getRandomZ(data.getRadius()), data.getVelocity().x, data.getVelocity().y, data.getVelocity().z);

                if (!counterSet) {
                    counterRequirement = data.getSpawnRate();
                    counterSet = true;
                }
            }
        }
    }
}
