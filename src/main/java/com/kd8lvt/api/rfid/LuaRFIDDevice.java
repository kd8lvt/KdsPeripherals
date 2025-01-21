package com.kd8lvt.api.rfid;

import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.content.block.GenericModBlockEntity;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtString;

import java.util.Objects;

public class LuaRFIDDevice<T extends RFIDComponent> {
    public final double distance;
    public final ItemStack stack;
    public RFIDDevice device;
    public final ComponentType<T> componentType;
    public final boolean writeable;

    public LuaRFIDDevice(double distance, ComponentType<T> componentType, ItemStack stack, RFIDDevice device, boolean writeable) {
        this.distance = distance;
        this.device = device;
        this.stack = stack;
        this.writeable = writeable;
        this.componentType = componentType;
    }

    public static <T extends RFIDComponent> LuaRFIDDevice<T> of(GenericModBlockEntity be, boolean canWrite, Entity entity, ItemStack device, ComponentType<T> component) {
        double distance = Math.sqrt(be.getPos().getSquaredDistance(entity.getPos())); //Why can I only get squared distances lmao
        T dev = device.get(component);
        return new LuaRFIDDevice<>(distance, component, device, dev, canWrite);
    }

    @LuaFunction
    public final String getDeviceType() {
        return device.deviceType();
    }

    @LuaFunction
    public final double distance() {
        return distance;
    }

    @LuaFunction
    public final boolean writeable() {
        return writeable;
    }

    @LuaFunction
    public final String read(String key) {
        NbtElement val = device.read(key);
        if (val != null) return val.asString();
        return null;
    }

    //This method is bad.
    //I know its bad.
    //It works, though... somehow.
    @LuaFunction
    public final void write(String key, String value) throws LuaException {
        if (!writeable) throw new LuaException("This device isn't writeable!");
        T comp = stack.get(componentType);
        comp = (T)comp.write(key,NbtString.of(value));
        this.device = comp; //Update the lua representation of the device. I shouldn't really allow things to persist, but meh.
        stack.set(componentType,comp);
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