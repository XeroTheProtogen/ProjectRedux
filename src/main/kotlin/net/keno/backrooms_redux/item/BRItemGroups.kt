package net.keno.backrooms_redux.item

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.keno.backrooms_redux.BRCommonRegistry
import net.keno.backrooms_redux.BackroomsRedux
import net.minecraft.item.ItemConvertible
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object BRItemGroups {
    val FIRST_BATCH_GROUP: ItemGroup = createItemGroup("first_batch",
        ItemStack(BRCommonRegistry.YELLOW_WALLPAPER), FabricItemGroup.builder()
            .entries{_, entries -> add(entries, BRCommonRegistry.YELLOW_WALLPAPER, BRCommonRegistry.MOIST_CARPET,
                BRCommonRegistry.CEILING_TILES, BRCommonRegistry.TILE_LIGHTS)})

    val FIRST_BATCH_KEY: RegistryKey<ItemGroup> = getItemGroupKey(FIRST_BATCH_GROUP)

    private fun createItemGroup(name: String, icon: ItemStack, builder: ItemGroup.Builder): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, BackroomsRedux.modLoc(name),
            builder.displayName(Text.translatable("itemGroup.$name")).icon{icon}.build())
    }

    private fun add(entries: ItemGroup.Entries, vararg items: ItemConvertible) {
        Arrays.stream(items).distinct().forEach(entries::add)
    }

    fun getItemGroupKey(group: ItemGroup): RegistryKey<ItemGroup> {
        return Registries.ITEM_GROUP.getKey(group).get()
    }

    fun init() {

    }
}