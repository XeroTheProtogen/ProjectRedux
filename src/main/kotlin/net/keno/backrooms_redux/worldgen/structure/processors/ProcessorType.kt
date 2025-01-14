package net.keno.backrooms_redux.worldgen.structure.processors

import com.mojang.serialization.Lifecycle
import com.mojang.serialization.MapCodec
import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.SimpleRegistry

data class ProcessorType<T : PieceProcessor>(val codec: MapCodec<T>) {
    companion object {
        @JvmField
        val REGISTRY_KEY: RegistryKey<Registry<ProcessorType<*>>> = RegistryKey.ofRegistry(BackroomsRedux.modLoc("piece_processors"))

        @JvmField
        val REGISTRY: Registry<ProcessorType<*>> = SimpleRegistry(REGISTRY_KEY, Lifecycle.stable())
    }
}
