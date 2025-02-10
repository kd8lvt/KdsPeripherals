package com.kd8lvt.registry;

import com.kd8lvt.content.component.GenericModComponent;
import com.kd8lvt.content.component.rfid.PlayerCardComponent;
import com.kd8lvt.content.item.RFIDCard.RFIDCardComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;

public final class ModComponents extends ModRegistry.Registry<ComponentType<?>> {
    public ModComponents() {
        super(Registries.DATA_COMPONENT_TYPE);
    }

    public static ComponentType<PlayerCardComponent> PLAYER_CARD;
    public static ComponentType<RFIDCardComponent> RFID_CARD;

    public void registerAll() {
        PLAYER_CARD = register(PlayerCardComponent.DEFAULT);
        RFID_CARD = register(RFIDCardComponent.DEFAULT);
    }

    @SuppressWarnings("unchecked")
    public <T extends GenericModComponent> ComponentType<T> register(T comp) {
        return (ComponentType<T>)register(comp.id().getPath(),(ComponentType<T>)comp.type()).value();
    }

}
