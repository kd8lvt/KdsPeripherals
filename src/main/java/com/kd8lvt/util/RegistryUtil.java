package com.kd8lvt.util;

import net.minecraft.util.Identifier;

import static com.kd8lvt.KdsPeripherals.MOD_ID;

public interface RegistryUtil {
    static Identifier id(String path) {
        return Identifier.of(MOD_ID,path);
    }
}
