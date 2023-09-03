package com.cerebus.ironchests.registry

import com.cerebus.ironchests.IronChests
import org.bukkit.Material
import xyz.xenondevs.nova.addon.registry.BlockRegistry
import xyz.xenondevs.nova.data.world.block.property.Directional
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.item.options.BlockOptions
import xyz.xenondevs.nova.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.item.tool.VanillaToolTiers
import xyz.xenondevs.nova.world.block.sound.SoundGroup

@Init
object Blocks : BlockRegistry by IronChests.registry {
    
    private val CUSTOM_CHEST = BlockOptions(
        3.0,
        VanillaToolCategories.PICKAXE,
        VanillaToolTiers.STONE,
        true,
        SoundGroup.STONE,
        Material.IRON_BLOCK
    )
    
    val IRON_CHEST = block("iron_chest").blockOptions(CUSTOM_CHEST).properties(Directional.NORMAL).register()

}
