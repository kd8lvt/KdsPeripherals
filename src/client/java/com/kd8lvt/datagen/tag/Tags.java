package com.kd8lvt.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class Tags {
    public static void all(FabricDataGenerator.Pack pack) {
        pack.addProvider(BlockTags::new);
    }
}
