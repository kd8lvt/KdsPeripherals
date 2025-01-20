package com.kd8lvt.registry;

public final class ModRegistry {
    public static void init() {
        ModComponents.init();
        ModItems.init();

        ModTooltips.init();
    }
}
