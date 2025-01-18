package com.kd8lvt.registry;

import com.kd8lvt.content.item.GenericModItem;
import com.kd8lvt.content.item.rfid.RFIDGenericCardItem;
import com.kd8lvt.content.item.rfid.RFIDPlayerCardItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import static com.kd8lvt.util.RegistryUtil.id;

public final class ModItems {
    final static ModRegistry<Item> REGISTRY = new ModRegistry<>(Registries.ITEM);

    public static RFIDPlayerCardItem PLAYER_RFID_CARD;
    public static RFIDGenericCardItem GENERIC_RFID_CARD;

    //Blocks
    public static BlockItem RFID_INTERFACE;

    public static void init() {
        //Items
        PLAYER_RFID_CARD = REGISTRY.register(new RFIDPlayerCardItem(), "player_rfid_card");
        GENERIC_RFID_CARD = REGISTRY.register(new RFIDGenericCardItem(), "generic_rfid_card");

        //Blocks
        RFID_INTERFACE = REGISTRY.register(new BlockItem(ModBlocks.RFID_INTERFACE,new Item.Settings()), "rfid_interface");
    }
}
