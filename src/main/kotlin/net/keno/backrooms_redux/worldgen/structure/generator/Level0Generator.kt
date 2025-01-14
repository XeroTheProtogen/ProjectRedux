package net.keno.backrooms_redux.worldgen.structure.generator

import net.keno.backrooms_redux.BackroomsRedux
import net.keno.backrooms_redux.worldgen.structure.pieces.BRStructurePieces
import net.keno.backrooms_redux.worldgen.structure.pieces.pools.LevelPiecePools
import net.keno.backrooms_redux.worldgen.structure.pieces.pools.PiecePool
import net.minecraft.nbt.NbtCompound
import net.minecraft.structure.*
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.ServerWorldAccess

object Level0Generator {
    fun addPieces(structureTemplateManager: StructureTemplateManager, pos: BlockPos, rotation: BlockRotation,
                  holder: StructurePiecesHolder, random: Random) {
        val piecePool = LevelPiecePools.LEVEL_POOLS[LevelPiecePools.LEVEL_0_COMMON_POOL]

        holder.addPiece(getPiece(piecePool!!, structureTemplateManager, pos, rotation, random))
    }

    private fun getPiece(pool: PiecePool, structureTemplateManager: StructureTemplateManager,
                         pos: BlockPos, rotation: BlockRotation, random: Random): Piece {
        return Piece(structureTemplateManager, 0, pool.getRandomPiece(random),
            pos, rotation, random)
    }

    class Piece: SimpleStructurePiece {
        companion object {
            fun createPlacementData(rotation: BlockRotation): StructurePlacementData {
                return StructurePlacementData().setRotation(rotation).setPosition(BlockPos(0,0,0))
                    .setMirror(BlockMirror.NONE).setLiquidSettings(StructureLiquidSettings.APPLY_WATERLOGGING)
                    .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS)
            }
        }

        constructor(manager: StructureTemplateManager, length: Int,
                    identifier: Identifier, pos: BlockPos, rotation: BlockRotation, random: Random): super(BRStructurePieces.LEVEL_0, length,
            manager, identifier,
            identifier.toString(), createPlacementData(rotation), pos)

        constructor(manager: StructureTemplateManager, nbt: NbtCompound): super(BRStructurePieces.LEVEL_0, nbt, manager,
            {createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")))})

        override fun writeNbt(context: StructureContext?, nbt: NbtCompound?) {
            super.writeNbt(context, nbt)
            nbt!!.putString("Rot", placementData.rotation.name)
        }

        override fun handleMetadata(
            metadata: String?,
            pos: BlockPos?,
            world: ServerWorldAccess?,
            random: Random?,
            boundingBox: BlockBox?
        ) {

        }
    }
}