package com.kd8lvt.registry;

import com.kd8lvt.content.item.PlayerRFIDCard;
import com.kd8lvt.util.GenericModThing;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static com.kd8lvt.util.RegistryUtil.id;

public final class ModItems {
    public static Item PLAYER_CARD;

    static void init() {
        PLAYER_CARD = register(new PlayerRFIDCard(id("player_rfid_card")));
    }

    public static <T extends Item & GenericModThing> T register(T item) {
        return Registry.register(Registries.ITEM,item.id(),item);
    }
}
