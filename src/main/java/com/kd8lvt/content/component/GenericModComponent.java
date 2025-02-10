package com.kd8lvt.content.component;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.api.content.KdThing;
import com.kd8lvt.api.rfid.component.RFIDComponent;
import net.minecraft.component.ComponentType;

public abstract class GenericModComponent implements KdThing {
    public abstract RFIDComponentCodec<? extends RFIDComponent> codec();
    public abstract ComponentType<? extends RFIDComponent> type();
}
