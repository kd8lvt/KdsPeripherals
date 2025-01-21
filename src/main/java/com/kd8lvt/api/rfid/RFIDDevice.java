package com.kd8lvt.api.rfid;

import com.kd8lvt.util.GenericModThing;
import com.kd8lvt.util.RegistryUtil;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

public interface RFIDDevice extends GenericModThing {
    NbtElement read(String key);
    void write(String key, NbtElement value) throws LuaException;
    String deviceType();
    default Identifier id() {
        return RegistryUtil.id(deviceType());
    }
}
