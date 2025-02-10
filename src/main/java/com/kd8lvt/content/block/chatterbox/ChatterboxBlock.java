package com.kd8lvt.content.block.chatterbox;

import com.kd8lvt.api.content.block.KdBlockWithEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;

public class ChatterboxBlock extends KdBlockWithEntity<ChatterboxBlockEntity> {
    public ChatterboxBlock() {
        super(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),RegistryUtil.id("chatterbox"));
    }

    @Override
    public BlockEntityType<ChatterboxBlockEntity> getBlockEntityType() {
        return ModBlockEntities.CHATTERBOX;
    }
}
