package com.kd8lvt.datagen;

import com.kd8lvt.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

import static net.minecraft.data.client.Models.*;

public class Models extends FabricModelProvider {
    public Models(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blocks) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator items) {
        items.register(ModItems.PLAYER_CARD, HANDHELD);
    }
}
