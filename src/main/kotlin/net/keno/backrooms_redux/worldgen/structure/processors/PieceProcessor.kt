package net.keno.backrooms_redux.worldgen.structure.processors

import com.mojang.serialization.Codec
import net.minecraft.block.BlockState
import net.minecraft.util.math.random.Random
import net.minecraft.world.StructureWorldAccess

interface PieceProcessor {
    companion object {
        val PROCESSOR_CODEC: Codec<PieceProcessor> = ProcessorType
            .REGISTRY.codec.dispatch("type",
                PieceProcessor::getType) { type -> type.codec }
    }

    fun shouldApply(blockState: BlockState, random: Random, structureWorldAccess: StructureWorldAccess): Boolean

    fun apply(blockState: BlockState, random: Random, structureWorldAccess: StructureWorldAccess)

    fun getType(): ProcessorType<*>
}