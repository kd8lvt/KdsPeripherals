package com.kd8lvt.registry;

import com.kd8lvt.content.block.GenericModBlock;
import com.kd8lvt.content.block.biometric_identifier.BiometricIdentifierBlock;
import com.kd8lvt.content.block.chatterbox.ChatterboxBlock;
import com.kd8lvt.content.block.faceid_scanner.FaceIDScannerBlock;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlock;
import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public final class ModBlocks {
    public final static ArrayList<GenericModBlock> BLOCKS_THAT_DROP_THEMSELVES = new ArrayList<>(); //For datagen
    public final static ArrayList<GenericModBlock> PICK = new ArrayList<>();
    public final static ArrayList<GenericModBlock> SHOVEL = new ArrayList<>();
    public final static ArrayList<GenericModBlock> AXE = new ArrayList<>();
    public final static ArrayList<GenericModBlock> HOE = new ArrayList<>();

    public final static ArrayList<GenericModBlock> BLOCKS = new ArrayList<>();
    public static GenericModBlock BIOMETRIC_IDENTIFIER;
    public static GenericModBlock FACEID_SCANNER;
    public static GenericModBlock RANGED_RFID_SCANNER;
    public static GenericModBlock RFID_SCANNER;
    public static GenericModBlock CHATTERBOX;

    public static void init() {
        RANGED_RFID_SCANNER = registerWithItem(new RangedRFIDScannerBlock());
        RFID_SCANNER = registerWithItem(new RFIDScannerBlock());
        BIOMETRIC_IDENTIFIER = registerWithItem(new BiometricIdentifierBlock());
        FACEID_SCANNER = registerWithItem(new FaceIDScannerBlock());
        CHATTERBOX = registerWithItem(new ChatterboxBlock(),new ArrayList<>(List.of(HarvestType.AXE,HarvestType.PICK)));
    }

    private static <T extends GenericModBlock> T register(T thing) {
        T ret = Registry.register(Registries.BLOCK,thing.id(),thing);
        BLOCKS.add(ret);
        return ret;
    }
    private static <T extends GenericModBlock> T registerWithItem(T thing,ArrayList<HarvestType> harvestTypes) {
        return registerWithItem(thing,true,harvestTypes);
    }
    private static <T extends GenericModBlock> T registerWithItem(T thing,boolean dropsSelf) {
        return registerWithItem(thing,dropsSelf,new ArrayList<>(List.of(HarvestType.PICK)));
    }
    private static <T extends GenericModBlock> T registerWithItem(T thing) {
        return registerWithItem(thing,true,new ArrayList<>(List.of(HarvestType.PICK)));
    }
    @SuppressWarnings("SameParameterValue")
    private static <T extends GenericModBlock> T registerWithItem(T thing, boolean dropsSelf,ArrayList<HarvestType> harvestTypes) {
        T ret = Registry.register(Registries.BLOCK,thing.id(),thing);
        ModItems.register(thing);
        BLOCKS.add(ret);
        if (dropsSelf) BLOCKS_THAT_DROP_THEMSELVES.add(ret);
        for (HarvestType harvestType : harvestTypes) {
            harvestType.addToTagQueue(ret);
        }
        return ret;
    }

    enum HarvestType {
        PICK,SHOVEL,AXE,HOE;
        void addToTagQueue(GenericModBlock block) {
            switch (this) {
                case PICK -> ModBlocks.PICK.add(block);
                case SHOVEL -> ModBlocks.SHOVEL.add(block);
                case AXE -> ModBlocks.AXE.add(block);
                case HOE -> ModBlocks.HOE.add(block);
            }
        }
    }
}
