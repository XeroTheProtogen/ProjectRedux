package net.keno.backrooms_redux.worldgen.structure.structures

import com.mojang.serialization.MapCodec
import net.keno.backrooms_redux.worldgen.structure.BRStructureTypes
import net.keno.backrooms_redux.worldgen.structure.generator.Level0Generator
import net.minecraft.structure.StructurePiecesCollector
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.random.ChunkRandom
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.structure.StructureType
import java.util.*

class Level0Structure(config: Config) : Structure(config) {
    companion object {
        val codec: MapCodec<Level0Structure> = createCodec(::Level0Structure)
    }

    override fun getStructurePosition(context: Context?): Optional<StructurePosition> {
        return getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG) { collector
            -> addPieces(collector, context!!)
        }
    }

    private fun addPieces(collector: StructurePiecesCollector, context: Context) {
        val chunkPos: ChunkPos = context.chunkPos
        val chunkRandom: ChunkRandom = context.random
        val blockPos = BlockPos(chunkPos.startX, 40, chunkPos.startZ)
        val blockRotation: BlockRotation = BlockRotation.NONE
        Level0Generator.addPieces(context.structureTemplateManager, blockPos, blockRotation, collector, chunkRandom)
    }

    override fun getType(): StructureType<*> {
        return BRStructureTypes.LEVEL_0
    }
}