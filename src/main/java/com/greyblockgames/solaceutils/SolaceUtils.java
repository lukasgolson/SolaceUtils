package com.greyblockgames.solaceutils;

import com.google.gson.*;
import com.greyblockgames.solaceutils.data.CapeData;
import com.greyblockgames.solaceutils.data.CosmeticsData;
import com.greyblockgames.solaceutils.data.UnusualEffectData;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class SolaceUtils implements ModInitializer {

    public static final String MODID = "solaceutils";
    public static final String NAME = "Solace Utils";
    public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(MODID + ":resources");
    public static final Logger logger = LogManager.getLogger(NAME);
    //private static final String capesConfig = "https://solacesmp.s3-us-west-2.amazonaws.com/config.json";
    private static final String capesConfig = "";
    public static CosmeticsData cosmeticsData = new CosmeticsData();

    JsonSerializer<UnusualEffectData> unusualEffectSerializer = (src, typeOfSrc, context) -> {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rate", src.getSpawnRate());
        jsonObject.addProperty("height", src.getHeightScale());
        jsonObject.addProperty("particleType", Registry.PARTICLE_TYPE.getId(src.getParticleEffect().getType()).toString());
        return jsonObject;
    };

    JsonDeserializer<UnusualEffectData> unusualEffectDeserializer = new JsonDeserializer<UnusualEffectData>() {
        @Override
        public UnusualEffectData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            int rate = jsonObject.get("rate").getAsInt();
            double height = jsonObject.get("height").getAsDouble();
            String IDParts[] = jsonObject.get("particleType").getAsString().split(":", 2);
            ParticleType effect = Registry.PARTICLE_TYPE.get(new Identifier(IDParts[0], IDParts[1]));

            return new UnusualEffectData(rate, height, (DefaultParticleType) effect);
        }
    };


    Gson gson = new GsonBuilder().setVersion(1).serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).registerTypeAdapter(UnusualEffectData.class, unusualEffectSerializer).registerTypeAdapter(UnusualEffectData.class, unusualEffectDeserializer).create();


    @Override
    public void onInitialize() {

        if (!capesConfig.isEmpty()) {
            try {
                String capeInfoJson = URLReader(new URL(capesConfig), Charset.defaultCharset());
                cosmeticsData = gson.fromJson(capeInfoJson, CosmeticsData.class);

            } catch (IOException e) {
                logger.warn("Unable to get cosmetic ownership list...");
                logger.warn(e);
            }

            downloadCapes();

        } else {
            cosmeticsData.capeList.put("lukas", new CapeData("https://solacesmp.s3-us-west-2.amazonaws.com/lukas.png", 0));

            cosmeticsData.EarOwners.add("f64eef3c-19b6-4943-b6d4-ad64f683bf9d");
            cosmeticsData.capeOwners.put("f64eef3c-19b6-4943-b6d4-ad64f683bf9d", "lukas");
            cosmeticsData.unusualOwners.put("f64eef3c-19b6-4943-b6d4-ad64f683bf9d", new UnusualEffectData(20, 0.8, ParticleTypes.ENCHANT));

            // Registry.PARTICLE_TYPE.getId()

            logger.info(gson.toJson(cosmeticsData));
            downloadCapes();
        }
    }

    private void downloadCapes() {

        for (Map.Entry<String, CapeData> entry : cosmeticsData.capeList.entrySet()) {


            try {
                URL url = new URL(entry.getValue().getUrl());
                RESOURCE_PACK.addTexture(new Identifier(MODID, "capes/" + entry.getKey().toLowerCase(Locale.ROOT).replaceAll(" ", "_")), ImageIO.read(url));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RRPCallback.EVENT.register(a -> a.add(RESOURCE_PACK));
        }
    }


    private String URLReader(URL url, Charset encoding) throws IOException {
        String content;
        try (Scanner scanner = new Scanner(url.openStream(), String.valueOf(encoding))) {
            content = scanner.useDelimiter("\\A").next();
        }
        return content;
    }

}



