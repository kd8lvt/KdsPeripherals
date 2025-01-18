package com.kd8lvt.datagen;

import com.kd8lvt.registry.ModBlocks;
import com.kd8lvt.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;

public class ModelProvider extends FabricModelProvider {
    ItemModelGenerator items;
    BlockStateModelGenerator blocks;

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blocks) {
        this.blocks=blocks;
        genericBlock(ModBlocks.RFID_INTERFACE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator items) {
        this.items = items;
        handheld(ModItems.GENERIC_RFID_CARD);
        handheld(ModItems.PLAYER_RFID_CARD);
    }

    public void genericBlock(Block block) {
        blocks.registerSimpleCubeAll(block);
    }

    public void handheld(Item item) {
        items.register(
            item,
            Models.HANDHELD
        );
    }
}
