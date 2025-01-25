package com.kd8lvt.datagen.lang;

import com.kd8lvt.registry.ModBlocks;
import com.kd8lvt.registry.ModItems;
import com.kd8lvt.registry.ModTranslations;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class English extends FabricLanguageProvider {
    static TranslationBuilder builder;
    public English(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        builder = translationBuilder;
        items();
        creative_tabs();
        blocks();
        tooltips();
    }

    private static void items() {
        builder.add(ModItems.PLAYER_CARD,"Player RFID Card");
        builder.add(ModItems.RFID_CARD,"RFID Card");
    }

    private static void creative_tabs() {
        builder.add(ModTranslations.MAIN_TAB,"Kd's Peripherals");
    }

    private static void blocks() {
        builder.add(ModBlocks.RANGED_RFID_SCANNER, "Ranged RFID Scanner");
        builder.add(ModBlocks.RFID_SCANNER, "RFID Scanner");
        builder.add(ModBlocks.BIOMETRIC_IDENTIFIER, "Biometric Identifier");
        builder.add(ModBlocks.FACEID_SCANNER, "Face-ID Scanner");
    }

    private static void tooltips() {
        builder.add(ModTranslations.BOUND,"Bound to %s");
        builder.add(ModTranslations.UNBOUND,"Not bound");
        builder.add(ModTranslations.STORED_DATA,"Storing %sB of data");
        builder.add(ModTranslations.NO_STORED_DATA,"No data stored");
    }
}