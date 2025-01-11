package net.keno.backrooms_redux.worldgen.structure.placement_type

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.Vec3i
import net.minecraft.world.gen.chunk.placement.StructurePlacement
import net.minecraft.world.gen.chunk.placement.StructurePlacementCalculator
import net.minecraft.world.gen.chunk.placement.StructurePlacementType
import java.util.Optional

class GuaranteedPlacementType(val saltNumber: Int) : StructurePlacement(Vec3i.ZERO, FrequencyReductionMethod.DEFAULT,
    1f, saltNumber, Optional.empty()) {
    companion object {
        @JvmField
        val CODEC: MapCodec<GuaranteedPlacementType> = RecordCodecBuilder.mapCodec {instance ->
            instance.group(
                Codec.INT.stable().fieldOf("salt").forGetter(GuaranteedPlacementType::saltNumber)
            ).apply(instance, ::GuaranteedPlacementType)
        }
        @JvmField
        val TYPE: StructurePlacementType<GuaranteedPlacementType> = StructurePlacementType { CODEC }
    }

    override fun isStartChunk(calculator: StructurePlacementCalculator?, chunkX: Int, chunkZ: Int): Boolean {
        return true
    }

    override fun shouldGenerate(calculator: StructurePlacementCalculator?, chunkX: Int, chunkZ: Int): Boolean {
        return true
    }

    override fun getType(): StructurePlacementType<*> {
        return TYPE
    }
}