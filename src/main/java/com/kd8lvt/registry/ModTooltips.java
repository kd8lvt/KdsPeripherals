package com.kd8lvt.registry;

import com.kd8lvt.util.GenericModThing;
import net.minecraft.util.Identifier;

import static com.kd8lvt.util.RegistryUtil.id;

public class ModTooltips {
    public static String STORED_DATA;
    public static String NO_STORED_DATA;
    public static String BOUND;
    public static String UNBOUND;

    public static void init() {
        STORED_DATA = createKey(id("generic.stored_data"));
        NO_STORED_DATA = createKey(id("generic.no_stored_data"));

        BOUND = createKey(id("generic.bound"));
        UNBOUND = createKey(id("generic.unbound"));
    }

    public static String createKey(Identifier id) {
        return id.toTranslationKey("tooltip");
    }
    public static String createKey(GenericModThing thing) {
        return createKey(thing.id());
    }
    public static String createKey(Identifier id,String suffix) {
        return id.toTranslationKey("tooltip",suffix);
    }
    public static String createKey(GenericModThing thing, String suffix) {
        return createKey(thing.id(),suffix);
    }
}
