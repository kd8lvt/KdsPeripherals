package com.kd8lvt.content.block.rfid_interface;

import com.google.errorprone.annotations.concurrent.GuardedBy;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class RFIDInterfacePeripheral implements IPeripheral {
    private final @GuardedBy("computers") Set<IComputerAccess> computers = new HashSet<>(1);
    Modes mode = Modes.READ;
    String writeData = "";
    String nameToSet = "";

    public void emitRFIDScan(String stackData) {
        synchronized (computers) {
            for (var computer : computers) {
                computer.queueEvent("rfid_scan", stackData);
            }
        }
    }

    public void emitRFIDWrite(String oldData, String newData) {
        synchronized(computers) {
            for (var computer : computers) {
                computer.queueEvent("rfid_write",oldData,newData);
            }
        }
    }

    @LuaFunction
    public final void setWriteData(Object data) throws LuaException {
        if (writeData.getBytes().length > 1024) {
            throw new LuaException("Length of the RFID write data must not exceed 1024 bytes!");
        }
        writeData=data.toString();
    }

    @LuaFunction
    public final String getMode() {
        return mode.toString();
    }

    @LuaFunction
    public final void setMode(String luaStr) throws LuaException {
        Modes mode = Modes.fromLuaString(luaStr);
        if (mode == null) throw new LuaException("Invalid mode - expected R/READ or W/WRITE");
        else this.mode = mode;
    }

    @LuaFunction
    public final void setCardName(String name) {
        this.nameToSet = name;
    }

    @LuaFunction
    public final void stopSettingCardName() {
        this.nameToSet = "";
    }

    @Override
    public String getType() {
        return "rfidInterface";
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return false;
    }

    @Override
    public void attach(IComputerAccess computer) {
        IPeripheral.super.attach(computer);
        synchronized (computers) {
            computers.add(computer);
        }
    }

    @Override
    public void detach(IComputerAccess computer) {
        IPeripheral.super.detach(computer);
        synchronized (computers) {
            computers.remove(computer);
        }
    }

    public enum Modes {
        READ,
        WRITE;
        public String toString() {
            return (this == READ?"READ":"WRITE");
        }
        public static Modes fromLuaString(String luaStr) {
            return (luaStr.equalsIgnoreCase("r") || luaStr.equalsIgnoreCase("read")?READ:
                    (luaStr.equalsIgnoreCase("w") || luaStr.equalsIgnoreCase("write")?WRITE:
                        null)
                   );
        }
    }
}
