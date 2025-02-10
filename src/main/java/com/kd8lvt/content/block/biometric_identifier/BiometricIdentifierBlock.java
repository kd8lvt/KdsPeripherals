package com.kd8lvt.content.block.biometric_identifier;

import com.kd8lvt.api.content.block.KdBlockWithEntity;
import com.kd8lvt.registry.ModBlockEntities;
import com.kd8lvt.util.RegistryUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BiometricIdentifierBlock extends KdBlockWithEntity<BiometricIdentifierBlockEntity> {
    public BiometricIdentifierBlock(Identifier id) {
        super(Settings.create().strength(Blocks.STONE.getBlastResistance(),Blocks.STONE.getHardness()).solid().sounds(BlockSoundGroup.STONE),id);
    }

    public BiometricIdentifierBlock() {
        this(RegistryUtil.id("biometric_identifier"));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof BiometricIdentifierBlockEntity be)) return super.onUse(state, world, pos, player, hit);
        be.peripheral().fireEvent("biometric_read",player.getUuidAsString());
        return ActionResult.success(true);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof BiometricIdentifierBlockEntity be)) {
            super.onSteppedOn(world, pos, state, entity);
            return;
        }
        be.peripheral().fireEvent("biometric_read",entity.getUuidAsString());
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public BlockEntityType<BiometricIdentifierBlockEntity> getBlockEntityType() {
        return ModBlockEntities.BIOMETRIC_IDENTIFIER;
    }
}
