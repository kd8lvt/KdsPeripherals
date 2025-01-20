package com.kd8lvt.api;

import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface BindableItem {
    BindableItem bind(PlayerEntity entity);
    boolean isBound();

    @Nullable
    UUID getBoundUuid();
    @Nullable
    String getBoundName();
}
