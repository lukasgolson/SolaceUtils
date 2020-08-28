package com.greyblockgames.solaceutils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.greyblockgames.solaceutils.data.CosmeticsData;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SolaceUtils implements ModInitializer {

    public static final String MODID = "solaceutils";
    public static final String NAME = "Solace Utils";
    public static final Logger logger = LogManager.getLogger(NAME);
    private static final String capesConfig = "https://pastebin.com/raw/GqUDj7Hr";
    public static CosmeticsData cosmeticsData = new CosmeticsData();

    RuntimeResourcePack

    Gson gson = new GsonBuilder().setVersion(1).serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();

    public static String URLReader(URL url, Charset encoding) throws IOException {
        String content;
        try (Scanner scanner = new Scanner(url.openStream(), String.valueOf(encoding))) {
            content = scanner.useDelimiter("\\A").next();
        }
        return content;
    }

    @Override
    public void onInitialize() {

        try {
            String t = URLReader(new URL(capesConfig), Charset.defaultCharset());

        } catch (IOException e) {
            logger.warn("Unable to get cosmetic ownership list...");
            logger.warn(e);
        }


    }


}
