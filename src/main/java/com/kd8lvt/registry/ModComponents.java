package com.kd8lvt.registry;

import com.kd8lvt.content.component.GenericModComponent;
import com.kd8lvt.content.component.rfid.PlayerCardComponent;
import com.kd8lvt.content.item.RFIDCard.RFIDCardComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public final class ModComponents {
    public static ComponentType<PlayerCardComponent> PLAYER_CARD;
    public static ComponentType<RFIDCardComponent> RFID_CARD;

    public static void init() {
        PLAYER_CARD = register(PlayerCardComponent.DEFAULT);
        RFID_CARD = register(RFIDCardComponent.DEFAULT);
    }

    @SuppressWarnings("unchecked")
    public static <T extends GenericModComponent> ComponentType<T> register(T comp) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE,comp.id(),(ComponentType<T>)comp.type());
    }

}
