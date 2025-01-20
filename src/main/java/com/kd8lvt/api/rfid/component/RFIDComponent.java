package com.kd8lvt.api.rfid.component;

import com.kd8lvt.api.rfid.RFIDDevice;
import com.kd8lvt.content.item.PlayerRFIDCard;
import com.kd8lvt.util.GenericModComponent;
import net.minecraft.component.ComponentType;
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

    @Override
    public NbtElement read(String key) {
        return comp.get(key);
    }
    @Override
    public void write(String key, NbtElement value) {
        comp.put(key,value);
    }
}
