package com.kd8lvt.content.component;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.content.item.PlayerRFIDCard;
import com.kd8lvt.util.GenericModThing;
import net.minecraft.component.ComponentType;

public abstract class GenericModComponent implements GenericModThing {
    @SuppressWarnings("unused")
    public abstract RFIDComponentCodec<PlayerRFIDCard.PlayerCardComponent> codec();
    public abstract ComponentType<PlayerRFIDCard.PlayerCardComponent> type();
}
