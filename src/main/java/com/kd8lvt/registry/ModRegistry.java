package com.kd8lvt.registry;

public final class ModRegistry {
    //Is this class superfluous? Probably.
    public static void init() {
        //Init stage 1 - stuff with info that is required for most other things
        ModComponents.init();

        //Init stage 2 - blocks & their entities
        ModBlocks.init();
        ModBlockEntities.init();

        //Init stage 3 - items
        ModItems.init();

        //Init stage 4 - stuff that requires info from a bunch of other places
        ModTranslations.init();
        ModCreativeTabs.init();
    }
}
