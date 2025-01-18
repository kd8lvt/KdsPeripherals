package com.kd8lvt.registry;


import com.kd8lvt.content.item.GenericModItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class ModTooltips {
    //I'll be so real, most of this class is just boilerplate for more organized tooltip storage.
    //Also makes language generation less of a nightmare.

    public static void init() {
        RFID_ITEM_INFO.init();
    }

    private static final class ModTooltipRegistry {
        private final static HashMap<Identifier, String> registered = new HashMap<>();

        public static String register(Identifier id, String translationKey) {
            if (registered.containsKey(id))
                throw new RuntimeException("You can't register the same id more than once!");
            return registered.put(id, translationKey);
        }

        public static Identifier getId(String tooltip) throws Throwable {
            Optional<Map.Entry<Identifier, String>> opt = registered.entrySet().stream().filter(entry -> Objects.equals(entry.getValue(), tooltip)).findFirst();
            if (opt.isEmpty()) throw new RuntimeException("That tooltip wasn't found!").fillInStackTrace();
            return opt.get().getKey();
        }

        public static String get(Identifier id) {
            return registered.get(id);
        }
    }

    private static class ItemboundTooltipHelper {
        Identifier item;
        String withSuffix(String suffix) {
            return item.toTranslationKey("tooltip",suffix);
        }
        String basic() {
            return item.toTranslationKey("tooltip");
        }
        ItemboundTooltipHelper(GenericModItem item) {
            this.item=Registries.ITEM.getId(item);
        }
    }

    //Tooltips go here
    public static class RFID_ITEM_INFO {
        public static class GENERIC_CARD {
            public static String NO_DATA_SET;
            public static String DATA_SET;
            static void init() {
                ItemboundTooltipHelper helper = new ItemboundTooltipHelper(ModItems.GENERIC_RFID_CARD);

                NO_DATA_SET = helper.withSuffix("no_data_set");
                DATA_SET = helper.withSuffix("data_set");
            }
        }
        public static class PLAYER_CARD {
            public static String NOT_BOUND;
            public static String BOUND;
            static void init() {
                ItemboundTooltipHelper helper = new ItemboundTooltipHelper(ModItems.PLAYER_RFID_CARD);

                NOT_BOUND = helper.withSuffix("not_bound");
                BOUND = helper.withSuffix("bound");
            }
        }
        static void init() {
            GENERIC_CARD.init();
            PLAYER_CARD.init();
        }
    }
}