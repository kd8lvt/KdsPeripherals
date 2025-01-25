package com.kd8lvt.registry;

import com.kd8lvt.api.peripheral.PeripheralBlockEntity;
import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.block.GenericModBlockEntity;
import com.kd8lvt.content.block.biometric_identifier.BiometricIdentifierBlockEntity;
import com.kd8lvt.content.block.chatterbox.ChatterboxBlockEntity;
import com.kd8lvt.content.block.faceid_scanner.FaceIDScannerBlockEntity;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlockEntity;
import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlockEntity;
import dan200.computercraft.api.peripheral.PeripheralLookup;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;

public final class ModBlockEntities {
    public static BlockEntityType<RangedRFIDScannerBlockEntity> RANGED_RFID_SCANNER = BlockEntityType.Builder.create(RangedRFIDScannerBlockEntity::new, ModBlocks.RANGED_RFID_SCANNER).build();
    public static BlockEntityType<RFIDScannerBlockEntity> RFID_SCANNER = BlockEntityType.Builder.create(RFIDScannerBlockEntity::new, ModBlocks.RFID_SCANNER).build();
    public static BlockEntityType<BiometricIdentifierBlockEntity> BIOMETRIC_IDENTIFIER = BlockEntityType.Builder.create(BiometricIdentifierBlockEntity::new, ModBlocks.BIOMETRIC_IDENTIFIER).build();
    public static BlockEntityType<FaceIDScannerBlockEntity> FACEID_SCANNER = BlockEntityType.Builder.create(FaceIDScannerBlockEntity::new, ModBlocks.FACEID_SCANNER).build();
    public static BlockEntityType<ChatterboxBlockEntity> CHATTERBOX = BlockEntityType.Builder.create(ChatterboxBlockEntity::new, ModBlocks.CHATTERBOX).build();

    public static void init() {
        register(RANGED_RFID_SCANNER,ModBlocks.RANGED_RFID_SCANNER);
        register(RFID_SCANNER,ModBlocks.RFID_SCANNER);
        register(BIOMETRIC_IDENTIFIER,ModBlocks.BIOMETRIC_IDENTIFIER);
        register(FACEID_SCANNER,ModBlocks.FACEID_SCANNER);
        register(CHATTERBOX, ModBlocks.CHATTERBOX);
    }

    private static <T extends GenericModBlockEntity> void register(BlockEntityType<T> thing, GenericModBlock block) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE,block.id(),thing);

        if (thing.instantiate(new BlockPos(0,0,0),block.getDefaultState()) instanceof PeripheralBlockEntity) {
            PeripheralLookup.get().registerForBlockEntity((blockEntity,direction)->blockEntity instanceof PeripheralBlockEntity pBE ? pBE.peripheral() : null,thing);
        }
    }
}
