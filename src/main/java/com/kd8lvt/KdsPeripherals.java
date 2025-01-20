package com.kd8lvt;

import com.kd8lvt.registry.ModRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KdsPeripherals implements ModInitializer {
    public static final String MOD_ID = "kds_periphs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModRegistry.init();
    }
}