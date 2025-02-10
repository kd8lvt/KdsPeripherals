package com.kd8lvt.api.rfid;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface CustomLuaRFIDDeviceProvider {
    <T extends LuaRFIDDevice<?>> T rfidDevice(PeripheralBlockEntity be, boolean canWrite, Entity player, ItemStack device);
}
