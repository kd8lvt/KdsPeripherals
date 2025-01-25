package com.kd8lvt.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class Loot {
    public static void all(FabricDataGenerator.Pack pack) {
        pack.addProvider(BlockLoot::new);
    }
}
