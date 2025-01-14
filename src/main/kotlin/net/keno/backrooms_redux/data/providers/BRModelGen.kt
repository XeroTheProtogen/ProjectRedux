package net.keno.backrooms_redux.data.providers

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.keno.backrooms_redux.BRCommonRegistry
import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator

class BRModelGen(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(generator: BlockStateModelGenerator?) {
        generator!!.registerSimpleCubeAll(BRCommonRegistry.YELLOW_WALLPAPER)
        generator.registerSimpleCubeAll(BRCommonRegistry.MOIST_CARPET)
        generator.registerSimpleCubeAll(BRCommonRegistry.CEILING_TILES)
    }

    override fun generateItemModels(generator: ItemModelGenerator?) {
        BackroomsRedux.logger.info("No items to generate models for")
    }
}