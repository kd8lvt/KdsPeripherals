package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.rfid.CustomLuaRFIDDeviceProvider;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.api.rfid.RFIDItem;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.lua.ObjectLuaTable;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class RFIDScannerPeripheral extends GenericModPeripheral<RFIDScannerBlockEntity> implements IPeripheral {
    public static int RANGE = 2;
    public static boolean canWrite=true;
    RFIDScannerPeripheral(RFIDScannerBlockEntity be) {
        super(be.id(),be);
    }

    @LuaFunction
    public final ObjectLuaTable scanForDevices() {
        RFIDScannerBlockEntity be = this.blockEntity();
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
            List<? extends ItemEntity> rfidItemEntitiesInRange = world.getEntitiesByType(TypeFilter.equals(ItemEntity.class),(entity)->entity.getStack().getItem() instanceof RFIDItem<?>);
            for (Object item : playersWithRFID) {
                if (item instanceof ItemEntity entity) {
                    ItemStack stack = entity.getStack();
                    if (stack.getItem() instanceof RFIDItem) {
                        if (stack.getItem() instanceof CustomLuaRFIDDeviceProvider provider)
                            devices.put(idx++, provider.rfidDevice(be, canWrite, entity, stack));
                        else devices.put(idx++, LuaRFIDDevice.of(be, canWrite, entity, stack));
                    }
                }
            }
        }
        return new ObjectLuaTable(devices);
    }
}
