package com.greyblockgames.solaceutils.mixins;


import com.greyblockgames.solaceutils.data.UnusualEffectData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

  @Accessor
  public abstract World getworld();



    int counter = 60;
    int counterRequirement = counter;
    boolean counterSet = false;


    @Environment(EnvType.CLIENT)
    @Inject(method = "tickMovement()V", at = @At(value = "RETURN"))
    private void tickMovementMixin(CallbackInfo ci) {

       /* if (((GameProfileMixinAccess) super).GBG_hasUnusualEffect()) {
            counter++;
            if (counter >= counterRequirement) {
                counter = 0;
                UnusualEffectData data = ((GameProfileMixinAccess) entity).GBG_getUnusualEffect();

                entity.clientWorld.addParticle(data.getParticleEffect(), entity.getParticleX(data.getRadius()), entity.getBodyY(data.getHeightScale()), entity.getParticleZ(data.getRadius()), data.getVelocity().x, data.getVelocity().y, data.getVelocity().z);

                if (!counterSet) {
                    counterRequirement = data.getSpawnRate();
                    counterSet = true;
                }
            }
        }*/

    }
}
