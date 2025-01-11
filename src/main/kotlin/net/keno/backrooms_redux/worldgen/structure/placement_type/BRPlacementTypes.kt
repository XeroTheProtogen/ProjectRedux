package net.keno.backrooms_redux.worldgen.structure.placement_type

import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.gen.chunk.placement.StructurePlacement
import net.minecraft.world.gen.chunk.placement.StructurePlacementType

object BRPlacementTypes {
    @JvmField
    val GUARANTEED_PLACEMENT = register("guaranteed_placement", GuaranteedPlacementType.TYPE)

    private fun <T : StructurePlacement> register(name: String, placementType: StructurePlacementType<T>): StructurePlacementType<T> {
        return Registry.register(Registries.STRUCTURE_PLACEMENT, BackroomsRedux.modLoc(name), placementType)
    }

    fun init() {
        BackroomsRedux.logger.info("Registering placement types")
    }
}