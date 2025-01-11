package net.keno.backrooms_redux.worldgen.biome

import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.biome.Biome

object BRBiomes {
    @JvmField
    val LEVEL_0_BIOME: RegistryKey<Biome> = RegistryKey.of(RegistryKeys.BIOME, BackroomsRedux.modLoc("level_0"))
}