package com.kd8lvt.content.block;

import com.kd8lvt.util.GenericModThing;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public abstract class GenericModBlock extends Block implements GenericModThing {
    private final Identifier id;
    public GenericModBlock(Settings settings,Identifier id) {
        super(settings);
        this.id=id;
    }

    public GenericModBlock(Identifier id) {
        this(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),id);
    }

    @Override
    public Identifier id() {
        return id;
    }
}
