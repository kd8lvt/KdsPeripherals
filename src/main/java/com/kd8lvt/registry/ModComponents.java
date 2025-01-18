package com.kd8lvt.registry;

import com.kd8lvt.api.rfid.RFIDDevice;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;

public final class ModComponents {
    private final static ModRegistry<ComponentType<?>> REGISTRY = new ModRegistry<>(Registries.DATA_COMPONENT_TYPE);

    public static ComponentType<RFIDDevice> RFID_DEVICE;

    public static void init() {
        RFID_DEVICE = REGISTRY.register(ComponentType.<RFIDDevice>builder().codec(RFIDDevice.CODEC).build(), "rfid_device");
    }
}
