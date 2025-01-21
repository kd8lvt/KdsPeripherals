package com.kd8lvt.api.rfid;

import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public interface CustomLuaRFIDDeviceProvider {
    LuaRFIDDevice rfidDevice(RangedRFIDScannerBlockEntity be, boolean canWrite, ServerPlayerEntity player, ItemStack device);
}
