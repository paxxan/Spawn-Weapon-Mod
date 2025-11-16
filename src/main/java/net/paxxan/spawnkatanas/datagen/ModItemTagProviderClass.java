package net.paxxan.spawnkatanas.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.paxxan.spawnkatanas.SpawnKatanas;
import net.paxxan.spawnkatanas.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProviderClass extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProviderClass(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final TagKey<Item> SMELLY = TagKey.of(RegistryKeys.ITEM, Identifier.of(SpawnKatanas.MOD_ID, "smelly"));

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(SMELLY)
                .add(ModItems.BALANCE_KATANA);
    }
}
