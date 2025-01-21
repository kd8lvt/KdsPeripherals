package com.kd8lvt.api.rfid;

import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlockEntity;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Objects;

public class LuaRFIDDevice {
    public final double distance;
    public final RFIDDevice device;
    public final boolean writeable;

    public LuaRFIDDevice(double distance, RFIDDevice device, boolean writeable) {
        this.distance = distance;
        this.device = device;
        this.writeable = writeable;
    }

    public static LuaRFIDDevice of(RangedRFIDScannerBlockEntity be, boolean canWrite, ServerPlayerEntity player, ItemStack device) {
        double distance = Math.sqrt(be.getPos().getSquaredDistance(player.getPos())); //Why can I only get squared distances lmao
        RFIDDevice dev = null;
        if (device.getItem() instanceof RFIDItem<? extends RFIDComponent> rfidItem) {
            dev = device.getOrDefault(rfidItem.component_type, rfidItem.default_component);
        }
        return new LuaRFIDDevice(distance, dev, canWrite);
    }

    @LuaFunction
    public String getDeviceType() {
        return device.deviceType();
    }

    @LuaFunction
    public double distance() {
        return distance;
    }

    @LuaFunction
    public boolean writeable() {
        return writeable;
    }

    @LuaFunction
    public String read(String key) {
        NbtElement val = device.read(key);
        if (val != null) return val.asString();
        return null;
    }

    @LuaFunction
    public void write(String key, String value) throws LuaException {
        if (!writeable) throw new LuaException("This device isn't writeable!");
        device.write(key, NbtString.of(value));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LuaRFIDDevice) obj;
        return Double.doubleToLongBits(this.distance) == Double.doubleToLongBits(that.distance) &&
                Objects.equals(this.device, that.device) &&
                this.writeable == that.writeable;
    }
}