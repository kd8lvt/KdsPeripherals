package com.kd8lvt.registry;

import com.kd8lvt.KdsPeripherals;
import com.kd8lvt.api.registry.KdRegistry;

public final class ModRegistry {
    public static final ModComponents COMPONENTS = new ModComponents();
    public static final ModBlocks BLOCKS = new ModBlocks();
    public static final ModBlockEntities BLOCK_ENTITIES = new ModBlockEntities();
    public static final ModItems ITEMS = new ModItems();
    public static final ModCreativeTabs CREATIVE_TABS = new ModCreativeTabs();

    //Is this class superfluous? Probably.
    public static void init() {
        //Init stage 1 - stuff with info that is required for most other things
        COMPONENTS.registerAll();

        //Init stage 2 - blocks & their entities
        BLOCKS.registerAll();
        BLOCK_ENTITIES.registerAll();

        //Init stage 3 - items
        ITEMS.registerAll();

        //Init stage 4 - stuff that requires info from a bunch of other places
        ModTranslations.init();
        CREATIVE_TABS.registerAll();
    }

    public static abstract class Registry<T> extends KdRegistry<T> {
        public Registry(net.minecraft.registry.Registry<T> registry) {super(registry);}
        
        @Override
        public String getNamespace() {return KdsPeripherals.MOD_ID;}
    }
}
