package com.kd8lvt.util;

import com.kd8lvt.api.codec.RFIDComponentCodec;
import com.kd8lvt.content.item.PlayerRFIDCard;
import net.minecraft.component.ComponentType;

public abstract class GenericModComponent implements GenericModThing {
    public abstract RFIDComponentCodec<PlayerRFIDCard.PlayerCardComponent> codec();
    public abstract ComponentType<PlayerRFIDCard.PlayerCardComponent> type();
}
