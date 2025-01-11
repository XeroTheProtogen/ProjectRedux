package net.keno.backrooms_redux

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.keno.backrooms_redux.data.listeners.LevelPieceListener
import net.keno.backrooms_redux.worldgen.structure.BRStructureTypes
import net.keno.backrooms_redux.worldgen.structure.BRStructures
import net.keno.backrooms_redux.worldgen.structure.pieces.BRStructurePieces
import net.keno.backrooms_redux.worldgen.structure.placement_type.BRPlacementTypes
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object BackroomsRedux : ModInitializer {
	@JvmField
	val logger: Logger = LoggerFactory.getLogger("backrooms_redux")

	@JvmField
	val GSON: Gson = GsonBuilder().create()

	private const val MODID = "backrooms_redux"

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(LevelPieceListener())

		BRPlacementTypes.init()
		BRStructurePieces.init()
		BRStructureTypes.init()
		BRStructures.init()
	}

	fun modLoc(path: String): Identifier {
		return Identifier.of(MODID, path)
	}
}