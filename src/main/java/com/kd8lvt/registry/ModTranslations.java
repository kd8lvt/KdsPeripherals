package com.kd8lvt.registry;

import com.kd8lvt.api.content.KdThing;
import net.minecraft.util.Identifier;

import static com.kd8lvt.util.RegistryUtil.id;

@SuppressWarnings("unused")
public class ModTranslations {
    public static String STORED_DATA;
    public static String NO_STORED_DATA;
    public static String BOUND;
    public static String UNBOUND;

    public static String MAIN_TAB;

    static void init() {
        tooltips();
        creativeTabs();
    }

    static void creativeTabs() {
        MAIN_TAB = createTab(id("main"));
    }

    static void tooltips() {
        STORED_DATA = createTooltip(id("generic.stored_data"));
        NO_STORED_DATA = createTooltip(id("generic.no_stored_data"));

        BOUND = createTooltip(id("generic.bound"));
        UNBOUND = createTooltip(id("generic.unbound"));
    }

    private static String createTab(Identifier id) {
        return id.toTranslationKey("itemGroup");
    }

    private static String createTooltip(Identifier id) {
        return id.toTranslationKey("tooltip");
    }
    private static String createTooltip(KdThing thing) {
        return createTooltip(thing.id());
    }
    private static String createTooltip(Identifier id, String suffix) {
        return id.toTranslationKey("tooltip",suffix);
    }
    private static String createTooltip(KdThing thing, String suffix) {
        return createTooltip(thing.id(),suffix);
    }
}
