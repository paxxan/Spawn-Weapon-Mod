package net.paxxan.spawnkatanas.component;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.paxxan.spawnkatanas.SpawnKatanas;

public class ModComponents {

    public static final ComponentType<Integer> FURY_COMBO = Registry.register(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(SpawnKatanas.MOD_ID, "fury_combo"),
        ComponentType.<Integer>builder().codec(Codec.INT).build()
);
    public static void initialize() {
        SpawnKatanas.LOGGER.info("Registering {} components", SpawnKatanas.MOD_ID);


    }
}