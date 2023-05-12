package com.greyblockgames.solaceutils.data;

import com.google.gson.*;
import java.lang.reflect.Type;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class GSONSerializers {

    public static JsonSerializer<UnusualEffectData> unusualEffectSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rate", src.getSpawnRate());
        jsonObject.addProperty("height", src.getHeightScale());
        jsonObject.addProperty("radius", src.getRadius());
        jsonObject.addProperty("velX", src.getVelocity().x);
        jsonObject.addProperty("velY", src.getVelocity().y);
        jsonObject.addProperty("velZ", src.getVelocity().z);


        jsonObject.addProperty("particleType", Registry.PARTICLE_TYPE.getId(src.getParticleEffect().getType()).toString());
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
            ParticleType<? extends net.minecraft.particle.ParticleEffect> effect = Registry.PARTICLE_TYPE.get(new Identifier(IDParts[0], IDParts[1]));

            return new UnusualEffectData(rate, height, radius, new Vector3d(vecx, vecy, vecz), (DefaultParticleType) effect);
        }
    };

}
