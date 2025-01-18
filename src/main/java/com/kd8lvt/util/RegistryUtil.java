package com.kd8lvt.util;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.kd8lvt.KdsPeripherals.MOD_ID;

public final class RegistryUtil {
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    @Nullable
    public static <T> Identifier idOf(T thing) {
        RegistryEntry<T> entry = RegistryEntry.of(thing);
        if (!entry.hasKeyAndValue()) return null;
        Optional<RegistryKey<T>> key = entry.getKey();

        //Gotta love intellisense coming up with the most deranged syntax "sugar"
        // if (key.isEmpty()) return null;
        // else return key.getValue();
        return key.map(RegistryKey::getValue).orElse(null);
    }
}
