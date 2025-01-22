package com.kd8lvt.api.rfid.component;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.content.component.GenericModComponent;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;

import java.util.UUID;

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

    public void ensureHasUUID() {
        NbtCompound comp = this.comp;
        if (comp.get("uuid") == null) {
            NbtCompound copy = comp.copy();
            copy.put("uuid",NbtHelper.fromUuid(UUID.randomUUID()));
            comp.copyFrom(copy);
        }
        this.comp = comp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RFIDComponent other) {
            this.ensureHasUUID();
            other.ensureHasUUID();
            return NbtHelper.toUuid(this.comp.get("uuid")).equals(NbtHelper.toUuid(other.comp.get("uuid")));
        }
        return false;
    }
}
