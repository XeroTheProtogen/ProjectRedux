package net.keno.backrooms_redux.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.keno.backrooms_redux.BRCommonRegistry
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class BRBlockTagGen(fabricDataOutput: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>):
    FabricTagProvider.BlockTagProvider(fabricDataOutput, completableFuture) {
    override fun configure(wrapperLookup: RegistryWrapper.WrapperLookup?) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(BRCommonRegistry.YELLOW_WALLPAPER)
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(BRCommonRegistry.YELLOW_WALLPAPER)
    }
}