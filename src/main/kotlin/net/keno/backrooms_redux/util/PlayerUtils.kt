package net.keno.backrooms_redux.util

import keno.piecedgenlib.api.player.PlayerSpawnManipulator
import keno.piecedgenlib.impl.player.data.AllowedRespawnDimensions
import keno.piecedgenlib.impl.player.data.DefaultRespawnPos
import keno.piecedgenlib.impl.player.data.PGLDataAttachments
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object PlayerUtils {
    fun initializerPlayerSpawnRestrictions(player: ServerPlayerEntity, defaultSpawnPos: BlockPos = player.blockPos,
                                           vararg identifiers: Identifier) {
        if (!player.hasAttached(PGLDataAttachments.defaultRespawnPos) &&
            !player.hasAttached(PGLDataAttachments.allowedRespawnDimensions)) {
            PlayerSpawnManipulator.initializePlayerDefaultSpawn(
                player, defaultSpawnPos,
                RegistryKey.of(RegistryKeys.WORLD, identifiers[0])
            )
            val filteredIds: List<Identifier> = identifiers.filter { it != identifiers[0] }
            for (id in filteredIds) {
                PlayerSpawnManipulator.addAllowedDimension(player, id)
            }
        }
    }

    fun allowSpawnDimension(player: ServerPlayerEntity, vararg identifiers: Identifier) {
        if (player.hasAttached(PGLDataAttachments.allowedRespawnDimensions)) {
            for (id in identifiers) {
                val playerData = player.getAttached(PGLDataAttachments.allowedRespawnDimensions)
                if (!playerData!!.isDimensionAllowed(id)) {
                    PlayerSpawnManipulator.addAllowedDimension(player, id)
                }
            }
        }
    }

    fun blockSpawnDimension(player: ServerPlayerEntity, vararg identifiers: Identifier) {
        if (player.hasAttached(PGLDataAttachments.allowedRespawnDimensions)) {
            val allowedRespawnDimensions: AllowedRespawnDimensions? = player.getAttached(PGLDataAttachments.allowedRespawnDimensions)
            for (id in identifiers) {
                if (allowedRespawnDimensions!!.isDimensionAllowed(id)) {
                    PlayerSpawnManipulator.removeAllowedDimension(player, id)
                    allowedRespawnDimensions.removeDimension(id)
                }
            }
            if (player.hasAttached(PGLDataAttachments.defaultRespawnPos)) {
                val defaultSpawnPos: DefaultRespawnPos? = player.getAttached(PGLDataAttachments.defaultRespawnPos)
                if (allowedRespawnDimensions!!.isDimensionAllowed(defaultSpawnPos!!.dimensionKey)) {
                    PlayerSpawnManipulator.changePlayerDefaultSpawn(player, defaultSpawnPos.pos, RegistryKey
                        .of(RegistryKeys.WORLD, identifiers[0]))
                }
            }
        }
    }

    fun changePlayerDefaultSpawn(player: ServerPlayerEntity, newPos: BlockPos, dimension: Identifier?) {
        if (player.hasAttached(PGLDataAttachments.defaultRespawnPos)) {
            val dimensionKey: RegistryKey<World> = if (dimension == null) {
                player.getAttached(PGLDataAttachments.defaultRespawnPos)!!.dimensionKey
            } else {
                RegistryKey.of(RegistryKeys.WORLD, dimension)
            }
            PlayerSpawnManipulator.changePlayerDefaultSpawn(player, newPos, dimensionKey)
        }
    }
}