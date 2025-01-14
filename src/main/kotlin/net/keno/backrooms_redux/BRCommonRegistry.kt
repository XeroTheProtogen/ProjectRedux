package net.keno.backrooms_redux

import net.keno.backrooms_redux.block.LampBlock
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

object BRCommonRegistry {
    // Blocks
    @JvmField
    val YELLOW_WALLPAPER: Block = registerBlock("yellow_wallpaper", Block(AbstractBlock
        .Settings.create().requiresTool().hardness(1.5f)))

    @JvmField
    val MOIST_CARPET: Block = registerBlock("moist_carpet", Block(AbstractBlock.Settings
        .copy(Blocks.WHITE_WOOL).strength(-1f, 3600000.0F).pistonBehavior(PistonBehavior.BLOCK)))

    @JvmField
    val CEILING_TILES: Block = registerBlock("ceiling_tile", Block(AbstractBlock.Settings
        .copy(Blocks.STONE).strength(-1f, 3600000.0f).pistonBehavior(PistonBehavior.BLOCK)))

    @JvmField
    val TILE_LIGHTS: Block = registerBlock("tile_light", LampBlock(AbstractBlock
        .Settings.copy(Blocks.GLOWSTONE).strength(-1f, 3600000.0f)
        .pistonBehavior(PistonBehavior.BLOCK).luminance{state -> if (state.get(LampBlock.LIT)) 10 else 0}))

    private fun registerBlock(name: String, block: Block): Block {
        registerBlocKItem(name, block, Item.Settings())
        return Registry.register(Registries.BLOCK, BackroomsRedux.modLoc(name), block)
    }

    private fun registerBlocKItem(name: String, block: Block, settings: Item.Settings): BlockItem {
        return Registry.register(Registries.ITEM, BackroomsRedux.modLoc(name),
            BlockItem(block, settings))
    }

    fun init() {

    }
}