package com.kd8lvt.registry;

import com.kd8lvt.api.content.block.KdBlock;
import com.kd8lvt.content.block.biometric_identifier.BiometricIdentifierBlock;
import com.kd8lvt.content.block.chatterbox.ChatterboxBlock;
import com.kd8lvt.content.block.faceid_scanner.FaceIDScannerBlock;
import com.kd8lvt.content.block.ranged_rfid_scanner.RangedRFIDScannerBlock;
import com.kd8lvt.content.block.rfid_scanner.RFIDScannerBlock;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

public final class ModBlocks extends ModRegistry.Registry<Block> {
    public ModBlocks() {
        super(Registries.BLOCK);
    }
    
    public final static ArrayList<KdBlock> BLOCKS_THAT_DROP_THEMSELVES = new ArrayList<>(); //For datagen
    public final static ArrayList<KdBlock> PICK = new ArrayList<>();
    public final static ArrayList<KdBlock> SHOVEL = new ArrayList<>();
    public final static ArrayList<KdBlock> AXE = new ArrayList<>();
    public final static ArrayList<KdBlock> HOE = new ArrayList<>();

    public final static ArrayList<KdBlock> BLOCKS = new ArrayList<>();
    public static KdBlock BIOMETRIC_IDENTIFIER;
    public static KdBlock FACEID_SCANNER;
    public static KdBlock RANGED_RFID_SCANNER;
    public static KdBlock RFID_SCANNER;
    public static KdBlock CHATTERBOX;

    @Override
    public void registerAll() {
        RANGED_RFID_SCANNER = registerWithItem(new RangedRFIDScannerBlock());
        RFID_SCANNER = registerWithItem(new RFIDScannerBlock());
        BIOMETRIC_IDENTIFIER = registerWithItem(new BiometricIdentifierBlock());
        FACEID_SCANNER = registerWithItem(new FaceIDScannerBlock());
        CHATTERBOX = registerWithItem(new ChatterboxBlock(),new ArrayList<>(List.of(HarvestType.AXE,HarvestType.PICK)));
    }
    private <T extends KdBlock> T registerWithItem(T thing,ArrayList<HarvestType> harvestTypes) {
        return registerWithItem(thing,true,harvestTypes);
    }
    private <T extends KdBlock> T registerWithItem(T thing) {
        return registerWithItem(thing,true,new ArrayList<>(List.of(HarvestType.PICK)));
    }
    @SuppressWarnings({ "SameParameterValue", "unchecked" })
    private <T extends KdBlock> T registerWithItem(T thing, boolean dropsSelf,ArrayList<HarvestType> harvestTypes) {
        T ret = (T)register(thing.id().getPath(),thing).value();
        ModRegistry.ITEMS.register(thing);
        BLOCKS.add(ret);
        if (dropsSelf) BLOCKS_THAT_DROP_THEMSELVES.add(ret);
        for (HarvestType harvestType : harvestTypes) {
            harvestType.addToTagQueue(ret);
        }
        return ret;
    }

    enum HarvestType {
        PICK,SHOVEL,AXE,HOE;
        void addToTagQueue(KdBlock block) {
            switch (this) {
                case PICK -> ModBlocks.PICK.add(block);
                case SHOVEL -> ModBlocks.SHOVEL.add(block);
                case AXE -> ModBlocks.AXE.add(block);
                case HOE -> ModBlocks.HOE.add(block);
            }
        }
    }
}
