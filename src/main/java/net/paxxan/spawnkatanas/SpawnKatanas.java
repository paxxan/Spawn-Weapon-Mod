package net.paxxan.spawnkatanas;

import net.fabricmc.api.ModInitializer;
import net.paxxan.spawnkatanas.component.ModComponents;
import net.paxxan.spawnkatanas.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnKatanas implements ModInitializer {
	public static final String MOD_ID = "spawn_katanas";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModComponents.initialize();

	}
}