package io.cerebus.ironchests.invui

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import xyz.xenondevs.invui.gui.TabGui
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.controlitem.TabItem
import xyz.xenondevs.nova.util.playClickSound

class SimpleTabItem(private val tab: Int, private val activeItem: ItemBuilder, private val inactiveItem: ItemBuilder) : TabItem(tab) {
    
    override fun getItemProvider(gui: TabGui): ItemProvider {
        return if (gui.currentTab == tab) activeItem else inactiveItem
    }
    
    override fun handleClick(clickType: ClickType, player: Player, event: InventoryClickEvent) {
        super.handleClick(clickType, player, event)
        player.playClickSound()
    }
}