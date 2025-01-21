package com.kd8lvt.registry;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlock;
import com.kd8lvt.util.GenericModThing;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModBlocks {
    public static GenericModBlock RANGED_RFID_SCANNER;

    public static void init() {
        RANGED_RFID_SCANNER = register(new RangedRFIDScannerBlock());
    }

    private static <T extends Block> T register(T thing) {
        if (thing instanceof GenericModThing gThing) return Registry.register(Registries.BLOCK,gThing.id(),thing);
        throw new RuntimeException("Unexpected non-GenericModThing");
    }
}
