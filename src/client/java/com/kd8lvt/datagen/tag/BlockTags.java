package com.kd8lvt.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import com.kd8lvt.api.content.block.KdBlock;

import static com.kd8lvt.registry.ModBlocks.*;

public class BlockTags extends FabricTagProvider<Block> {
    public BlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BLOCK, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        harvestToolTags();
    }

    private void harvestToolTags() {
        FabricTagBuilder pickBuilder = this.getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.PICKAXE_MINEABLE);
        FabricTagBuilder shovelBuilder = this.getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.SHOVEL_MINEABLE);
        FabricTagBuilder axeBuilder = this.getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.AXE_MINEABLE);
        FabricTagBuilder hoeBuilder = this.getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.HOE_MINEABLE);

        for (KdBlock block : PICK) pickBuilder.add(block);
        for (KdBlock block : SHOVEL) shovelBuilder.add(block);
        for (KdBlock block : AXE) axeBuilder.add(block);
        for (KdBlock block : HOE) hoeBuilder.add(block);
    }
}
