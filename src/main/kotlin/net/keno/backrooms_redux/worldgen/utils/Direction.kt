package net.keno.backrooms_redux.worldgen.utils

import net.minecraft.util.math.BlockPos
import kotlin.math.abs

enum class Direction {
    LEFT,
    RIGHT,
    FRONT,
    BACK,
    ORIGIN;

    companion object {
        fun getDirection(pos: BlockPos): Direction {
            if (pos.x == 0 && pos.z == 0) {
                return ORIGIN
            }

            val isXRight = pos.x > 0
            val isZUp = pos.z > 0

            return if (isXRight) {
                if (isZUp) {
                    if (pos.x > pos.z) {
                        RIGHT
                    } else {
                        FRONT
                    }
                } else {
                    if (pos.x > abs(pos.z)) {
                        RIGHT
                    } else {
                        BACK
                    }
                }
            } else {
                return if (isZUp) {
                    if (abs(pos.x) > pos.z) {
                        LEFT
                    } else {
                        FRONT
                    }
                } else {
                    if (abs(pos.x) > abs(pos.z)) {
                        RIGHT
                    } else {
                        BACK
                    }
                }
            }
        }
    }
}