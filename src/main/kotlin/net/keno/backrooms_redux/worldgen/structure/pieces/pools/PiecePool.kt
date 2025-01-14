package net.keno.backrooms_redux.worldgen.structure.pieces.pools

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.Identifier
import net.minecraft.util.math.random.Random

class PiecePool {
    private val pieceList: ArrayList<Identifier>
    val path: Identifier

    companion object {
        @JvmField
        val codec: Codec<PiecePool> = RecordCodecBuilder.create {instance -> instance.group(
            Identifier.CODEC.stable().fieldOf("pool").forGetter(PiecePool::path),
            Identifier.CODEC.stable().listOf().fieldOf("pieces").forGetter{piecePool -> piecePool.toList()}
        ).apply(instance, ::PiecePool)}
    }

    constructor(path: Identifier, pieceList: ArrayList<Identifier>) {
        this.pieceList = pieceList
        this.path = path
    }

    constructor(path: Identifier, pieceList: List<Identifier>) {
        this.path = path

        val arrayList: ArrayList<Identifier> = arrayListOf()
        val distinctList: List<Identifier> = pieceList.distinct()

        for (id in distinctList) {
            arrayList.add(id)
        }

        this.pieceList = arrayList
    }

    fun toList(): List<Identifier> {
        return pieceList.toList()
    }

    fun addPiece(pieceId: Identifier): Boolean {
        if (pieceList.contains(pieceId)) {
            return false
        }
        return pieceList.add(pieceId)
    }

    fun removePiece(pieceId: Identifier) {
        if (pieceList.contains(pieceId)) {
            pieceList.remove(pieceId)
        }
    }

    fun getRandomPiece(random: Random): Identifier {
        val listUpper = pieceList.size - 1

        val pieceIndex = random.nextBetween(0, listUpper)

        return pieceList[pieceIndex]
    }
}