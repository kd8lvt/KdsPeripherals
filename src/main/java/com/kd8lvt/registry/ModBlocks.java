package com.kd8lvt.registry;

import com.kd8lvt.content.block.rfid_interface.RFIDInterfaceBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

public final class ModBlocks {
    private final static ModRegistry<Block> REGISTRY = new ModRegistry<>(Registries.BLOCK);

    public static RFIDInterfaceBlock RFID_INTERFACE;

    public static void init() {
        RFID_INTERFACE = REGISTRY.register(new RFIDInterfaceBlock(), "rfid_interface");
    }
}
