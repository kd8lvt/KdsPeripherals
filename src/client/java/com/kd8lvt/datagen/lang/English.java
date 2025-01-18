package com.kd8lvt.datagen.lang;

import com.kd8lvt.registry.ModItems;
import com.kd8lvt.registry.ModTooltips;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class English extends FabricLanguageProvider {
    public English(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder builder) {
        items(builder);
        tooltips(builder);
    }

    public static void items(TranslationBuilder builder) {
        builder.add(ModItems.RFID_INTERFACE,"RFID Interface");
        builder.add(ModItems.PLAYER_RFID_CARD,"Player RFID Card");
        builder.add(ModItems.GENERIC_RFID_CARD,"Generic RFID Card");
    }

    public static void tooltips(TranslationBuilder builder) {
        builder.add(ModTooltips.RFID_ITEM_INFO.PLAYER_CARD.NOT_BOUND,"Unbound");
        builder.add(ModTooltips.RFID_ITEM_INFO.PLAYER_CARD.BOUND,"Bound to: %s");

        builder.add(ModTooltips.RFID_ITEM_INFO.GENERIC_CARD.NO_DATA_SET,"No Data Stored.");
        builder.add(ModTooltips.RFID_ITEM_INFO.GENERIC_CARD.DATA_SET,"Storing %s bytes of data!");
    }
}
