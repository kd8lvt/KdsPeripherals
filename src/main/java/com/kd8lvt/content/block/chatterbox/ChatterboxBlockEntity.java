package com.kd8lvt.content.block.chatterbox;

import com.kd8lvt.api.peripheral.GenericModPeripheral;
import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ChatterboxBlockEntity extends PeripheralBlockEntity {
    private GenericModPeripheral<ChatterboxBlockEntity> peripheral;

    public ChatterboxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHATTERBOX, pos, state);
    }

    @Override
    public GenericModPeripheral<ChatterboxBlockEntity> peripheral() {
        if (peripheral == null) peripheral = new ChatterboxPeripheral(id(),this);
        return peripheral;
    }

    @Override
    public Identifier id() {
        return RegistryUtil.id("chatterbox");
    }
}
