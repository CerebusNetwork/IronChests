package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import io.cerebus.ironchests.tileentity.DiamondChest
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
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
    
    private val IRON_CHEST_OPTIONS = BlockOptions(
        3.0,
        VanillaToolCategories.PICKAXE,
        VanillaToolTiers.STONE,
        true,
        SoundGroup.STONE,
        Material.IRON_BLOCK
    )
    
    private val GOLD_CHEST_OPTIONS = BlockOptions(
        3.0,
        VanillaToolCategories.PICKAXE,
        VanillaToolTiers.STONE,
        true,
        SoundGroup.STONE,
        Material.GOLD_BLOCK
    )
    private val DIAMOND_CHEST_OPTIONS = BlockOptions(
        3.0,
        VanillaToolCategories.PICKAXE,
        VanillaToolTiers.STONE,
        true,
        SoundGroup.STONE,
        Material.DIAMOND_BLOCK
    )
    
    val IRON_CHEST = tileEntity("iron_chest", ::IronChest).blockOptions(IRON_CHEST_OPTIONS).properties(Directional.NORMAL).register()
    val GOLD_CHEST = tileEntity("gold_chest", ::GoldChest).blockOptions(GOLD_CHEST_OPTIONS).properties(Directional.NORMAL).register()
    val DIAMOND_CHEST = tileEntity("diamond_chest", ::DiamondChest).blockOptions(DIAMOND_CHEST_OPTIONS).properties(Directional.NORMAL).register()

}
