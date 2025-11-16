package net.paxxan.spawnkatanas.damage_type;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.paxxan.spawnkatanas.SpawnKatanas;

public class ModDamageTypes {

    /*
     * Store the RegistryKey of our DamageType into a new constant called CUSTOM_DAMAGE_TYPE
     * The Identifier in use here points to our JSON file we created earlier.
     */
    public static final RegistryKey<DamageType> COMBO_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(SpawnKatanas.MOD_ID, "comboed"));

}

