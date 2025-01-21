package com.kd8lvt.api.rfid;

import com.kd8lvt.content.block.GenericModBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public interface CustomLuaRFIDDeviceProvider {
    <T extends LuaRFIDDevice> T rfidDevice(GenericModBlockEntity be, boolean canWrite, Entity player, ItemStack device);
}
