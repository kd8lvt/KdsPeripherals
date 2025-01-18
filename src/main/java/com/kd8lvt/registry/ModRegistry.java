package com.kd8lvt.registry;

import net.minecraft.registry.Registry;

import static com.kd8lvt.util.RegistryUtil.id;

class ModRegistry<T> {
    private final Registry<T> registry;

    ModRegistry(Registry<T> registry) {
        this.registry = registry;
    }

    <t extends T> t register(t thing, String idPath) {
        if (thing instanceof GenericModThing genericThing) genericThing.setId(id(idPath));
        return Registry.register(registry, id(idPath), thing);
    }
}
