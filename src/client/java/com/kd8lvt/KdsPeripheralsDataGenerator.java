package com.kd8lvt;

import com.kd8lvt.datagen.Models;
import com.kd8lvt.datagen.lang.Lang;
import com.kd8lvt.datagen.loot.Loot;
import com.kd8lvt.datagen.tag.Tags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class KdsPeripheralsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(Models::new);
		Tags.all(pack);
		Loot.all(pack);
		Lang.all(pack);
	}
}
