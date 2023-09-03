package com.cerebus.ironchests.registry

import com.cerebus.ironchests.IronChests
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init

@Init
object Items : ItemRegistry by IronChests.registry {
    
    val IRON_CHEST = registerItem(Blocks.IRON_CHEST)
    
}