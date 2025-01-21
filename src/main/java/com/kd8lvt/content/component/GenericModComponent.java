package com.kd8lvt.content.component;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import com.kd8lvt.util.GenericModThing;
import net.minecraft.component.ComponentType;

public abstract class GenericModComponent implements GenericModThing {
    @SuppressWarnings("unused")
    public abstract RFIDComponentCodec<? extends RFIDComponent> codec();
    public abstract ComponentType<? extends RFIDComponent> type();
}
