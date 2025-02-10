package com.kd8lvt.datagen;

import com.kd8lvt.api.content.KdThing;
import com.kd8lvt.api.content.block.KdBlock;
import com.kd8lvt.api.content.item.KdItem;
import com.kd8lvt.registry.ModItems;
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
    private final ArrayList<KdThing> alreadyGenerated = new ArrayList<>();
    private ItemModelGenerator items;
    private BlockStateModelGenerator blocks;
    public Models(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blocks) {
        this.blocks=blocks;

        for (KdBlock block : BLOCKS) {
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

        for (KdItem item : ITEMS) {
            if (!alreadyGenerated.contains(item)) {
                register(item,HANDHELD);
                defaultModelWarning(item.id().toString());
            }
        }
    }

    private void defaultModelWarning(String id) {
        LOGGER.warn("Generating default model for '{}'!",id);
    }

    private <T extends KdItem> void register(T item, Model model) {
        if (!alreadyGenerated.contains(item)) {
            items.register(item,model);
            alreadyGenerated.add(item);
        }
    }

    private <T extends KdBlock> void register(T block) {
        if (!alreadyGenerated.contains(block)){
            blocks.registerSimpleCubeAll(block);
            alreadyGenerated.add(block);
        }
    }
}
