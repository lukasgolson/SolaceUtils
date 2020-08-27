package com.greyblockgames.solaceutils;

import com.google.gson.Gson;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SolaceUtils implements ModInitializer {

    public static final String MODID = "solaceutils";
    public static final String NAME = "Solace Utils";
    public static final Logger logger = LogManager.getLogger(NAME);
    private static final String capesConfig = "";

    public static CosmeticsData cosmeticsData;


    @Override
    public void onInitialize() {
        logger.info(NAME + " has initialized.");

        cosmeticsData = new CosmeticsData();

        cosmeticsData.capeOwners.put("f64eef3c-19b6-4943-b6d4-ad64f683bf9d", "lukas");
        cosmeticsData.particleOwners.put("f64eef3c-19b6-4943-b6d4-ad64f683bf9d", "lukas");
        cosmeticsData.EarOwners.add("f64eef3c-19b6-4943-b6d4-ad64f683bf9d");



        Gson gson = new Gson();
        //String t = gson.toJson("test");
        String t = gson.toJson(cosmeticsData);
        logger.info(t.toString());
        logger.info("test");


    }


    private void GetCapes() {
       /* URL url = new URL(capesConfig);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.*/


    }
}
