package com.greyblockgames.solaceutils;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class SolaceUtils implements ModInitializer {

    public static final String MODID = "solaceutils";
    public static final String NAME = "Solace Utils";

    public static final Logger logger = LogManager.getLogger(NAME);

    public static HashMap<String, String> capeMap = new HashMap<>();


    @Override
    public void onInitialize() {

        logger.info(NAME + " has initialized.");

        capeMap.put("f64eef3c-19b6-4943-b6d4-ad64f683bf9d", "lukas");

    }
}
