package net.keno.backrooms_redux.worldgen.structure.pieces

import net.keno.backrooms_redux.BackroomsRedux
import net.keno.backrooms_redux.worldgen.structure.generator.Level0Generator
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.structure.StructurePieceType
import net.minecraft.util.Identifier

object BRStructurePieces {
    val LEVEL_0 = register(BackroomsRedux.modLoc("level_0")) { manager, nbt
        -> Level0Generator.Piece(manager.structureTemplateManager, nbt)
    }

    private fun register(name: Identifier, type: StructurePieceType): StructurePieceType {
        return Registry.register(Registries.STRUCTURE_PIECE, name, type)
    }

    fun init() {

    }
}