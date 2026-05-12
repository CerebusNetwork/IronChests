package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import io.cerebus.ironchests.IronChests.item
import io.cerebus.ironchests.IronChests.registerItem
import io.cerebus.ironchests.item.GoldChestUpgradeBehavior
import io.cerebus.ironchests.item.IronChestUpgradeBehavior
import io.cerebus.ironchests.item.WoodChestUpgradeBehavior
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage

@Init(stage = InitStage.PRE_PACK)
object Items {

    val IRON_CHEST = IronChests.registerItem(Blocks.IRON_CHEST)
    val GOLD_CHEST = IronChests.registerItem(Blocks.GOLD_CHEST)
    val DIAMOND_CHEST = IronChests.registerItem(Blocks.DIAMOND_CHEST)

    val WOOD_IRON_UPGRADE = IronChests.item("wood_iron_upgrade") {
        behaviors(WoodChestUpgradeBehavior)
        maxStackSize(1)
    }
    val IRON_GOLD_UPGRADE = IronChests.item("iron_gold_upgrade") {
        behaviors(IronChestUpgradeBehavior)
        maxStackSize(1)
    }
    val GOLD_DIAMOND_UPGRADE = IronChests.item("gold_diamond_upgrade") {
        behaviors(GoldChestUpgradeBehavior)
        maxStackSize(1)
    }

}
