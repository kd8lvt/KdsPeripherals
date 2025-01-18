package com.kd8lvt.util;

import java.util.concurrent.atomic.AtomicReference;

public class SingleWriteAtomic<T> {
    private final AtomicReference<T> value = new AtomicReference<>(null);
    public boolean trySet(T value) {
        if (this.value.get() != null) {
            this.value.set(value);
            return true;
        }
        return false;
    }
    public void set(T value) {
        //Because SOMEBODY (Intellisense) doesn't like when you ignore a return value.
        boolean b = trySet(value);
    }
    public T get() {
        return value.get();
    }
    public T getOrDefault(T defaultValue) {
        this.set(defaultValue);
        return this.get();
    }
}
