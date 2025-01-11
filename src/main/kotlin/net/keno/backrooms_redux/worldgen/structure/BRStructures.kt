package net.keno.backrooms_redux.worldgen.structure

import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.registry.*
import net.minecraft.world.gen.structure.Structure

object BRStructures {
    @JvmField
    val LEVEL_0_STRUCTURE: RegistryKey<Structure> = RegistryKey.of(RegistryKeys.STRUCTURE, BackroomsRedux.modLoc("level_0"))

    fun init() {
    }
}