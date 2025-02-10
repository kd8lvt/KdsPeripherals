package com.kd8lvt.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import static com.kd8lvt.registry.ModBlocks.BLOCKS;
import static com.kd8lvt.registry.ModItems.ITEMS;

public class ModCreativeTabs extends ModRegistry.Registry<ItemGroup> {
    public ModCreativeTabs() {
        super(Registries.ITEM_GROUP);
    }
    
    public static final ItemGroup MAIN_TAB = FabricItemGroup.builder()
        .displayName(Text.translatableWithFallback(ModTranslations.MAIN_TAB,"Kd's Peripherals"))
        .icon(()->new ItemStack(ModItems.RFID_CARD))
        .entries((ctx,entries)-> {
            entries.addAll(ITEMS.stream().map(ItemStack::new).toList());
            entries.addAll(BLOCKS.stream().filter(block->block.asItem() != Items.AIR).map(ItemStack::new).toList());
        })
    .build();

    public void registerAll() {
        register("main",MAIN_TAB);
    }
}
