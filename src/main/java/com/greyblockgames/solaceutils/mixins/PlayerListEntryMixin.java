package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.SolaceUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;
import java.util.Map;


@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin {

    @Accessor
    public abstract Map<MinecraftProfileTexture.Type, Identifier> getTextures();

    @Accessor
    public abstract GameProfile getProfile();


    @Environment(EnvType.CLIENT)
    @Inject(method = "loadTextures", at = @At(value = "RETURN"))
    public void injectIntoTextures(CallbackInfo ci) {
        if (!getTextures().containsKey(MinecraftProfileTexture.Type.CAPE) && SolaceUtils.cosmeticsData.capeOwners.containsKey(getProfile().getId().toString())) {
            Identifier capeIdentifier = new Identifier(SolaceUtils.MODID, "textures/capes/" + SolaceUtils.cosmeticsData.capeOwners.get(getProfile().getId().toString()).toLowerCase(Locale.ROOT).replaceAll(" ", "_") + ".png");
            getTextures().put(MinecraftProfileTexture.Type.CAPE, capeIdentifier);
        }

    }


   /* @Environment(EnvType.CLIENT)
    @Inject(method = "getCapeTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/PlayerListEntry;loadTextures()V"))
    public void injectIntoTextures(CallbackInfoReturnable<Identifier> cir) {
        if (SolaceUtils.cosmeticsData.capeOwners.containsKey(getProfile().getId().toString())) {
            Identifier capeIdentifier = new Identifier(SolaceUtils.MODID, "textures/capes/" + SolaceUtils.cosmeticsData.capeOwners.get(getProfile().getId().toString()) + ".png");
            getTextures().put(MinecraftProfileTexture.Type.CAPE, capeIdentifier);
        }
    }*/

}
