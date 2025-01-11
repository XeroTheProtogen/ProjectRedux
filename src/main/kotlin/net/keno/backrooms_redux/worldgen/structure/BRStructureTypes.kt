package net.keno.backrooms_redux.worldgen.structure

import com.mojang.serialization.MapCodec
import net.keno.backrooms_redux.BackroomsRedux
import net.keno.backrooms_redux.worldgen.structure.structures.Level0Structure
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.structure.StructureType

object BRStructureTypes {
    val LEVEL_0: StructureType<Level0Structure> = register(BackroomsRedux.modLoc("level_0"), Level0Structure.codec)

    private fun <T: Structure> register(name: Identifier, codec: MapCodec<T>): StructureType<T> {
        return Registry.register(Registries.STRUCTURE_TYPE, name, StructureType { codec })
    }

    fun init() {
    }
}