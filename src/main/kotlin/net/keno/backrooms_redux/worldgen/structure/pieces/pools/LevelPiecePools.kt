package net.keno.backrooms_redux.worldgen.structure.pieces.pools

import net.keno.backrooms_redux.BackroomsRedux.modLoc
import net.minecraft.util.Identifier

object LevelPiecePools {
    val LEVEL_0_COMMON_POOL = modLoc("level_0_common")

    var LEVEL_POOLS: HashMap<Identifier, PiecePool> = hashMapOf()
}