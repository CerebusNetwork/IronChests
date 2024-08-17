package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import io.cerebus.ironchests.item.GoldChestUpgradeBehavior
import io.cerebus.ironchests.item.IronChestUpgradeBehavior
import io.cerebus.ironchests.item.WoodChestUpgradeBehavior
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object Items : ItemRegistry by IronChests.registry {
    
    val IRON_CHEST = registerItem(Blocks.IRON_CHEST)
    val GOLD_CHEST = registerItem(Blocks.GOLD_CHEST)
    val DIAMOND_CHEST = registerItem(Blocks.DIAMOND_CHEST)
    
    val WOOD_IRON_UPGRADE = item("wood_iron_upgrade") {
        behaviors(WoodChestUpgradeBehavior)
        maxStackSize(1)
    }
    val IRON_GOLD_UPGRADE = item("iron_gold_upgrade") {
        behaviors(IronChestUpgradeBehavior)
        maxStackSize(1)
    }
    val GOLD_DIAMOND_UPGRADE = item("gold_diamond_upgrade") {
        behaviors(GoldChestUpgradeBehavior)
        maxStackSize(1)
    }
    
}
