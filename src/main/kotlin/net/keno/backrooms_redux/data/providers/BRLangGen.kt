package net.keno.backrooms_redux.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.keno.backrooms_redux.BRCommonRegistry
import net.keno.backrooms_redux.item.BRItemGroups
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class BRLangGen(dataOutput: FabricDataOutput, wrapper: CompletableFuture<RegistryWrapper.WrapperLookup>):
    FabricLanguageProvider(dataOutput, wrapper) {
    override fun generateTranslations(wrapperLookup: RegistryWrapper.WrapperLookup?, builder: TranslationBuilder?) {
        builder!!.add(BRCommonRegistry.YELLOW_WALLPAPER, "Yellow Wallpaper")
        builder.add(BRCommonRegistry.MOIST_CARPET, "Moist Carpet")
        builder.add(BRCommonRegistry.CEILING_TILES, "Ceiling Tile")
        builder.add(BRCommonRegistry.TILE_LIGHTS, "Tile Light")

        builder.add(BRItemGroups.FIRST_BATCH_KEY, "BR: The Beginning")
    }
}