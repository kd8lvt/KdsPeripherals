package com.kd8lvt.api.rfid.component;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.content.component.GenericModComponent;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

public abstract class RFIDComponent extends GenericModComponent implements RFIDDevice {
    public NbtCompound comp;
    public RFIDComponent() {
        this(new NbtCompound());
    }
    public RFIDComponent(NbtCompound encoded) {
        this.comp=encoded;
    }

    public abstract int maxBytes();

    @Override
    public NbtElement read(String key) {
        return comp.get(key);
    }
    @Override
    public RFIDComponent write(String key, NbtElement value) throws LuaException {
        NbtCompound copy = comp.copy();
        copy.put(key,value);
        comp = comp.copyFrom(copy);
        return this;
    }
}
