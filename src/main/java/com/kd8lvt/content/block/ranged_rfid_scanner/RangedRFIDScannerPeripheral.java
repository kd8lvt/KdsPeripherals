package com.kd8lvt.content.block.ranged_rfid_scanner;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.rfid.CustomLuaRFIDDeviceProvider;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDItem;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.ObjectLuaTable;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class RangedRFIDScannerPeripheral extends GenericModPeripheral<RangedRFIDScannerBlockEntity> implements IPeripheral {
    public static int RANGE = 8;
    public static boolean canWrite=false;
    RangedRFIDScannerPeripheral(RangedRFIDScannerBlockEntity be) {
        super(be.id(),be);
    }

    @LuaFunction
    public final ObjectLuaTable getNearbyDevices() {
        RangedRFIDScannerBlockEntity be = this.blockEntity();
        World w = be.getWorld();
        HashMap<Integer, LuaRFIDDevice> devices = new HashMap<>();
        int idx = 1;
        if (w instanceof ServerWorld world) {
            List<ServerPlayerEntity> playersInRange = world.getPlayers().stream().filter(entity->be.getPos().isWithinDistance(entity.getPos(),RANGE)).toList();
            List<ServerPlayerEntity> playersWithRFID = playersInRange.stream().filter(entity->entity.getInventory().contains(stack->stack.getItem() instanceof RFIDItem)).toList();
            for (ServerPlayerEntity player : playersWithRFID) {
                Inventory inv = player.getInventory();
                for (int i=0;i<inv.size();i++) {
                    ItemStack stack = inv.getStack(i);
                    if (stack.getItem() instanceof RFIDItem) {
                        if (stack.getItem() instanceof CustomLuaRFIDDeviceProvider provider) devices.put(idx++,provider.rfidDevice(be,canWrite,player,stack));
                        else devices.put(idx++, LuaRFIDDevice.of(be,canWrite,player,stack));
                    }
                }
            }
        }
        return new ObjectLuaTable(devices);
    }
}
