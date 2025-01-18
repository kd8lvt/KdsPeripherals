package com.kd8lvt.registry;

import com.kd8lvt.content.block.rfid_interface.RFIDInterfaceBlockEntity;
import dan200.computercraft.api.peripheral.PeripheralLookup;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;

public final class ModBlockEntities {
    private final static ModRegistry<BlockEntityType<?>> REGISTRY = new ModRegistry<>(Registries.BLOCK_ENTITY_TYPE);

    public static BlockEntityType<RFIDInterfaceBlockEntity> RFID_INTERFACE;

    public static void init() {
        RFID_INTERFACE = REGISTRY.register(BlockEntityType.Builder.create(RFIDInterfaceBlockEntity::new, ModBlocks.RFID_INTERFACE).build(), "rfid_interface");
        PeripheralLookup.get().registerForBlockEntity((be,direction)->be.peripheral,ModBlockEntities.RFID_INTERFACE);

    }
}
