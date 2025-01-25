package com.kd8lvt.datagen.loot;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.registry.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLoot extends FabricBlockLootTableProvider {
    protected BlockLoot(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for (GenericModBlock block : ModBlocks.BLOCKS_THAT_DROP_THEMSELVES) {
            addDrop(block);
        }
    }
}
