package com.kd8lvt.datagen.lang;

import com.kd8lvt.registry.ModItems;
import com.kd8lvt.registry.ModTooltips;
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
        tooltips();
    }

    private static void items() {
        builder.add(ModItems.PLAYER_CARD,"Player RFID Card");
    }

    private static void tooltips() {
        builder.add(ModTooltips.BOUND,"Bound to %s");
        builder.add(ModTooltips.UNBOUND,"Not bound");
        builder.add(ModTooltips.STORED_DATA,"Storing %s bytes of data");
        builder.add(ModTooltips.NO_STORED_DATA,"No data stored");
    }
}