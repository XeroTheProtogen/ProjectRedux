package net.keno.backrooms_redux.data.listeners

import com.google.gson.JsonObject
import com.mojang.serialization.JsonOps
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener
import net.keno.backrooms_redux.BackroomsRedux
import net.keno.backrooms_redux.worldgen.structure.pieces.pools.LevelPiecePools
import net.keno.backrooms_redux.worldgen.structure.pieces.pools.PiecePool
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.minecraft.util.profiler.Profiler
import java.io.InputStreamReader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

class LevelPieceListener : SimpleResourceReloadListener<Map<Identifier, PiecePool>> {
    override fun getFabricId(): Identifier {
        return BackroomsRedux.modLoc("level_piece_listener")
    }

    override fun load(manager: ResourceManager?, profiler: Profiler?, executor: Executor?): CompletableFuture<Map<Identifier, PiecePool>> {
        return CompletableFuture.supplyAsync({
            val map: HashMap<Identifier, PiecePool> = hashMapOf()
            for (resource in manager!!.findResources("worldgen/piece_pools") { id -> id.path.endsWith(".json") }.entries) {
                val inputStream = resource.value.inputStream

                inputStream.use {
                    val json = BackroomsRedux.GSON.fromJson(InputStreamReader(inputStream), JsonObject::class.java)
                    val piecePool = PiecePool.codec.decode(JsonOps.INSTANCE, json).getOrThrow().first

                    val id: Identifier = piecePool.path

                    if (map.containsKey(id)) {
                        for (identifier in piecePool.toList()) {
                            map[id]!!.addPiece(identifier)
                        }
                    } else {
                        BackroomsRedux.logger.info("Now registering the piece pool $id")
                        map[id] = piecePool
                    }
                }
            }

            map.toMap()
        },executor)
    }

    override fun apply(
        map: Map<Identifier, PiecePool>?,
        manager: ResourceManager?,
        profiler: Profiler?,
        executor: Executor?
    ): CompletableFuture<Void> {
        return CompletableFuture.runAsync({
            for (id in map!!.keys) {
                if (LevelPiecePools.LEVEL_POOLS.containsKey(id)) {
                    for (piece in map[id]!!.toList()) {
                        LevelPiecePools.LEVEL_POOLS[id]!!.addPiece(piece)
                    }
                } else {
                    LevelPiecePools.LEVEL_POOLS[id] = map[id]!!
                }
            }
        },executor)
    }
}