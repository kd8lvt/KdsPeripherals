package com.kd8lvt.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static com.kd8lvt.registry.ModBlocks.BLOCKS;
import static com.kd8lvt.registry.ModItems.ITEMS;
import static com.kd8lvt.util.RegistryUtil.id;

public class ModCreativeTabs {
    public static final ItemGroup MAIN_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable(ModTranslations.MAIN_TAB))
            .icon(()->new ItemStack(ModItems.RFID_CARD))
            .entries((ctx,entries)-> {
                        entries.addAll(ITEMS.stream().map(ItemStack::new).toList());
                        entries.addAll(BLOCKS.stream().filter(block->block.asItem() != Items.AIR).map(ItemStack::new).toList());
                    }).build();

    static void init() {
        Registry.register(Registries.ITEM_GROUP,id("main"),MAIN_TAB);
    }
}
