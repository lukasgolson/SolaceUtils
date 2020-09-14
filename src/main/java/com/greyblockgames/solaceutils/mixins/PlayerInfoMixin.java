package com.greyblockgames.solaceutils.mixins;

import com.greyblockgames.solaceutils.SolaceUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;
import java.util.Map;


@Mixin(PlayerInfo.class)
public abstract class PlayerInfoMixin {

    @Accessor("textureLocations")
    public abstract Map<MinecraftProfileTexture.Type, ResourceLocation> gettextureLocations();

    @Accessor
    public abstract GameProfile getProfile();


    @Environment(EnvType.CLIENT)
    @Inject(method = "registerTextures", at = @At(value = "RETURN"))
    public void injectIntoTextures(CallbackInfo ci) {
        if (!gettextureLocations().containsKey(MinecraftProfileTexture.Type.CAPE) && SolaceUtils.cosmeticsData.capeOwners.containsKey(getProfile().getId().toString())) {
            ResourceLocation capeIdentifier = new ResourceLocation(SolaceUtils.MODID, "textures/capes/" + SolaceUtils.cosmeticsData.capeOwners.get(getProfile().getId().toString()).toLowerCase(Locale.ROOT).replaceAll(" ", "_") + ".png");
            gettextureLocations().put(MinecraftProfileTexture.Type.CAPE, capeIdentifier);
        }

    }
}
