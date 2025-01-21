package com.kd8lvt.registry;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlock;
import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;

public final class ModBlocks {
    public final static ArrayList<GenericModBlock> BLOCKS = new ArrayList<>();
    public static GenericModBlock RANGED_RFID_SCANNER;
    public static GenericModBlock RFID_SCANNER;

    public static void init() {
        RANGED_RFID_SCANNER = registerWithItem(new RangedRFIDScannerBlock());
        RFID_SCANNER = registerWithItem(new RFIDScannerBlock());
    }

    private static <T extends GenericModBlock> T register(T thing) {
        T ret = Registry.register(Registries.BLOCK,thing.id(),thing);
        BLOCKS.add(ret);
        return ret;
    }

    private static <T extends GenericModBlock> T registerWithItem(T thing) {
        T ret = Registry.register(Registries.BLOCK,thing.id(),thing);
        BLOCKS.add(ret);
        ModItems.register(thing);
        return ret;
    }
}
