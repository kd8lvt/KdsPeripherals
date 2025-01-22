package com.kd8lvt.content.block.rfid_scanner;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.api.rfid.LuaRFIDDevice;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import dan200.computercraft.api.lua.ObjectLuaTable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RFIDScannerBlockEntity extends PeripheralBlockEntity {
    private RFIDScannerPeripheral<?> p;
    private int ticks = 0;
    private ObjectLuaTable oldDevices = new ObjectLuaTable(new HashMap<>());

    public RFIDScannerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RFID_SCANNER, pos, state);
    }
    public <T extends RFIDScannerBlockEntity> RFIDScannerBlockEntity(BlockEntityType<T> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("rfid_scanner");
    }

    private boolean uuidCompare(Map.Entry<Object,Object> that, Map.Entry<Object,Object> other) {
        if (!(that.getValue() instanceof LuaRFIDDevice<?> t)) return false;
        if (!(other.getValue() instanceof LuaRFIDDevice<?> o)) return false;
        return Objects.equals(t.uuid(), o.uuid());
    }

    private boolean compareTables(ObjectLuaTable first, ObjectLuaTable second) {
        Set<Map.Entry<Object,Object>> firstEntries = first.entrySet();
        Set<Map.Entry<Object,Object>> secondEntries = second.entrySet();
        boolean allFirstInSecond = firstEntries.stream().allMatch(firstEntry->secondEntries.stream().anyMatch(secondEntry->uuidCompare(firstEntry,secondEntry)));
        boolean allSecondInFirst = secondEntries.stream().allMatch(secondEntry->firstEntries.stream().anyMatch(firstEntry->uuidCompare(secondEntry,firstEntry)));
        return allFirstInSecond && allSecondInFirst;
    }

    public <T extends RFIDScannerBlockEntity> void every5Ticks(World world, BlockPos pos, BlockState state, T be) {
        ObjectLuaTable devices = be.peripheral().scanForDevices();
        if (compareTables(devices,oldDevices)) return;
        HashMap<Object,Object> devices_entered = new HashMap<>();
        HashMap<Object,Object> devices_left = new HashMap<>();
        int idx = 0;
        for (Map.Entry<Object,Object> entry : devices.entrySet().stream().filter(entry->!oldDevices.entrySet().contains(entry)).toList()) {
            devices_entered.put(idx++,entry.getValue());
        }

        for (Map.Entry<Object,Object> entry : oldDevices.entrySet().stream().filter(entry->!devices.entrySet().contains(entry)).toList()) {
            devices_left.put(idx++,entry.getValue());
        }
        be.peripheral().fireEvent("rfid_devices_entered_range", new ObjectLuaTable(devices_entered));
        be.peripheral().fireEvent("rfid_devices_left_range",new ObjectLuaTable(devices_left));
        oldDevices = devices;
    }

    //package-private to prevent lag from poorly-implemented overrides
    <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
        if (be instanceof RFIDScannerBlockEntity rfid) {
            ticks++;
            if (ticks % 5 == 0) {
                rfid.every5Ticks(world, pos, state, rfid);
                ticks = 0;
            }
        }
    }

    @Override
    public RFIDScannerPeripheral<?> peripheral() {
        if (p == null) p = new RFIDScannerPeripheral<>(this);
        return p;
    }
}
