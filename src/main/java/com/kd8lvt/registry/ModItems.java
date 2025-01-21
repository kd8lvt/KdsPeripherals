package com.kd8lvt.registry;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.item.GenericModItem;
import com.kd8lvt.content.item.PlayerRFIDCard;
import com.kd8lvt.content.item.RFIDCard;
import com.kd8lvt.util.GeneratedBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;

import static com.kd8lvt.util.RegistryUtil.id;

public final class ModItems {
    public final static ArrayList<GenericModItem> ITEMS = new ArrayList<>();
    public static PlayerRFIDCard PLAYER_CARD;
    public static RFIDCard RFID_CARD;

    static void init() {
        PLAYER_CARD = register(new PlayerRFIDCard(id("player_rfid_card")));
        RFID_CARD = register(new RFIDCard(id("rfid_card")));
    }

    public static <T extends GenericModItem> T register(T item) {
        T ret = Registry.register(Registries.ITEM,item.id(),item);
        ITEMS.add(ret);
        return ret;
    }

    public static <T extends GenericModBlock> void register(T block) {
        Registry.register(Registries.ITEM,block.id(),new GeneratedBlockItem(block));
    }
}

