package com.greyblockgames.solaceutils.data;

import com.google.gson.*;
import com.mojang.math.Vector3d;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class GSONSerializers {

    public static JsonSerializer<UnusualEffectData> unusualEffectSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rate", src.getSpawnRate());
        jsonObject.addProperty("height", src.getHeightScale());
        jsonObject.addProperty("radius", src.getRadius());
        jsonObject.addProperty("velX", src.getVelocity().x);
        jsonObject.addProperty("velY", src.getVelocity().y);
        jsonObject.addProperty("velZ", src.getVelocity().z);


        jsonObject.addProperty("particleType", Registry.PARTICLE_TYPE.getKey(src.getParticleEffect().getType()).toString());
        return jsonObject;
    };

    public static JsonDeserializer<UnusualEffectData> unusualEffectDeserializer = new JsonDeserializer<UnusualEffectData>() {
        @Override
        public UnusualEffectData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            int rate = jsonObject.get("rate").getAsInt();
            double height = jsonObject.get("height").getAsDouble();
            double radius = jsonObject.get("radius").getAsDouble();
            double vecx = jsonObject.get("velX").getAsDouble();
            double vecy = jsonObject.get("velY").getAsDouble();
            double vecz = jsonObject.get("velZ").getAsDouble();

            String[] IDParts = jsonObject.get("particleType").getAsString().split(":", 2);
            ParticleType<? extends net.minecraft.core.particles.ParticleOptions> effect = Registry.PARTICLE_TYPE.get(new ResourceLocation(IDParts[0], IDParts[1]));

            return new UnusualEffectData(rate, height, radius, new Vector3d(vecx, vecy, vecz), (SimpleParticleType) effect);
        }
    };

}
