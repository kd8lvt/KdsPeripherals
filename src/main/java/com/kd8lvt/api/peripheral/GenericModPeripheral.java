package com.kd8lvt.api.peripheral;

import com.kd8lvt.api.content.KdThing;
import dan200.computercraft.api.peripheral.AttachedComputerSet;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public abstract class GenericModPeripheral<BE extends PeripheralBlockEntity> implements KdThing, IPeripheral {
    private final Identifier id;
    private final BE be;
    public final AttachedComputerSet computers = new AttachedComputerSet();

    public void fireEvent(String event, Object ...args) {
        computers.queueEvent(event,args);
    }

    public GenericModPeripheral(Identifier id,BE be) {
        this.be = be;
        this.id = id;
    }
    public BE blockEntity() {return be;}

    @Override
    public Identifier id() {return id;}

    @Override
    public String getType() {return id().getPath();}
    @Override
    public boolean equals(@Nullable IPeripheral other) {return other == this;}
    @Override
    public void attach(IComputerAccess computer) {computers.add(computer);}
    @Override
    public void detach(IComputerAccess computer) {computers.remove(computer);}
}
