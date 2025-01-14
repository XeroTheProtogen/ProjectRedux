package net.keno.backrooms_redux.data

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.keno.backrooms_redux.data.providers.BRLangGen
import net.keno.backrooms_redux.data.providers.BRModelGen
import net.keno.backrooms_redux.data.providers.BRBlockTagGen

object BackroomsReduxDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()
		pack.addProvider(::BRModelGen)
		pack.addProvider{dataOutput, wrapper -> BRLangGen(dataOutput, wrapper)}
		pack.addProvider{dataOutput, wrapper -> BRBlockTagGen(dataOutput, wrapper)}
	}
}