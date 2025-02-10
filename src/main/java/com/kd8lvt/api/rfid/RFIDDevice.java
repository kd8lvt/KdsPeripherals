package com.kd8lvt.api.rfid;

import com.kd8lvt.api.content.KdThing;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.util.RegistryUtil;
import dan200.computercraft.api.lua.LuaException;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

public interface RFIDDevice extends KdThing {
    NbtElement read(String key);
    RFIDComponent write(String key, NbtElement value) throws LuaException;
    String deviceType();
    default Identifier id() {
        return RegistryUtil.id(deviceType());
    }
}
