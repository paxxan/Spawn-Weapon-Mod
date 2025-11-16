package net.paxxan.spawnkatanas.item;

import net.minecraft.util.Rarity;
import net.paxxan.spawnkatanas.component.ModComponents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.paxxan.spawnkatanas.SpawnKatanas;
import net.paxxan.spawnkatanas.item.custom.FuryKatana;
import net.paxxan.spawnkatanas.item.custom.BalanceKatana;

public class ModItems {

    public static final Item FURY_KATANA = registerItem("fury_katana",
            new FuryKatana(ToolMaterial.NETHERITE, 0f, 2f, new Item.Settings()
                    .component(ModComponents.FURY_COMBO, 0)
                    .rarity(Rarity.EPIC)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SpawnKatanas.MOD_ID, "fury_katana")))));

    public static final Item BALANCE_KATANA = registerItem("balance_katana",
            new BalanceKatana(ToolMaterial.NETHERITE, 3f, -2f, new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(SpawnKatanas.MOD_ID, "balance_katana")))));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SpawnKatanas.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SpawnKatanas.LOGGER.info("Registering Mod Items for " + SpawnKatanas.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(FURY_KATANA);
            entries.add(BALANCE_KATANA);
        });
    }
}
