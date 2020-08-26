package com.greyblockgames.solaceutils;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SolaceUtils implements ModInitializer {

    public static final String MODID = "solaceutils";
    public static final String NAME = "Solace Utils";

    private static final Logger logger = LogManager.getLogger(NAME);

    public static final Path mainDir = Paths.get(MODID);


    @Override
    public void onInitialize() {
        logger.info(NAME + " has initialized.");
    }
}
