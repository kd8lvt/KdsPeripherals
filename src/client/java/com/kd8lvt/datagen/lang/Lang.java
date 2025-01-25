package com.kd8lvt.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class Lang {
    public static void all(FabricDataGenerator.Pack pack) {
        pack.addProvider(English::new);
    }
}
