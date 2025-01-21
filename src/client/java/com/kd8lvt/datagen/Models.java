package com.kd8lvt.datagen;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.item.GenericModItem;
import com.kd8lvt.registry.ModItems;
import com.kd8lvt.util.GenericModThing;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;

import java.util.ArrayList;

import static com.kd8lvt.registry.ModBlocks.BLOCKS;
import static com.kd8lvt.registry.ModItems.ITEMS;
import static net.minecraft.data.client.Models.HANDHELD;

public class Models extends FabricModelProvider {
    private final ArrayList<GenericModThing> alreadyGenerated = new ArrayList<>();
    private ItemModelGenerator items;
    private BlockStateModelGenerator blocks;
    public Models(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blocks) {
        this.blocks=blocks;

        for (GenericModBlock block : BLOCKS) {
            if (!alreadyGenerated.contains(block)) {
                register(block);
                defaultModelWarning(block.id().toString());
            }
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator items) {
        this.items=items;
        register(ModItems.PLAYER_CARD, HANDHELD);
        register(ModItems.RFID_CARD, HANDHELD);

        for (GenericModItem item : ITEMS) {
            if (!alreadyGenerated.contains(item)) {
                register(item,HANDHELD);
                defaultModelWarning(item.id().toString());
            }
        }
    }

    private void defaultModelWarning(String id) {
        LOGGER.warn("Generating default model for '{}'!",id);
    }

    private <T extends GenericModItem> void register(T item, Model model) {
        if (!alreadyGenerated.contains(item)) {
            items.register(item,model);
            alreadyGenerated.add(item);
        }
    }

    private <T extends GenericModBlock> void register(T block) {
        if (!alreadyGenerated.contains(block)){
            blocks.registerSimpleCubeAll(block);
            alreadyGenerated.add(block);
        }
    }
}
