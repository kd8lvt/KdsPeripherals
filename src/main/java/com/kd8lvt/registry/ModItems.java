package com.kd8lvt.registry;

import java.util.ArrayList;

import com.kd8lvt.api.content.block.KdBlock;
import com.kd8lvt.api.content.item.KdItem;
import com.kd8lvt.content.item.PlayerRFIDCard;
import com.kd8lvt.content.item.RFIDCard;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.registry.Registries;

public final class ModItems extends ModRegistry.Registry<Item> {
    public static final ArrayList<KdItem> ITEMS = new ArrayList<>();
    public static PlayerRFIDCard PLAYER_CARD;
    public static RFIDCard RFID_CARD;

    public ModItems() {
        super(Registries.ITEM);
    }

    @Override
    public void registerAll() {
        PLAYER_CARD = new PlayerRFIDCard(id("player_rfid_card"));
        RFID_CARD = new RFIDCard(id("rfid_card"));
        register(PLAYER_CARD);
        register(RFID_CARD);
    }

    public <T extends KdItem> Item register(T thing) {
        ITEMS.add(thing);
        return register(thing.id().getPath(),thing).value();
    }

    public BlockItem register(KdBlock block) {
        BlockItem item = new BlockItem(block,new Settings());
        register(block.id().getPath(),item);
        return item;
    }
}

