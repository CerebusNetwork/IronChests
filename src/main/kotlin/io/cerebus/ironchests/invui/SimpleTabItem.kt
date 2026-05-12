package io.cerebus.ironchests.invui

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import xyz.xenondevs.invui.Click
import xyz.xenondevs.invui.item.AbstractTabGuiBoundItem
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.nova.util.playClickSound

class SimpleTabItem(private val tab: Int, private val activeItem: ItemProvider, private val inactiveItem: ItemProvider)
    : AbstractTabGuiBoundItem() {

    override fun getItemProvider(player: Player): ItemProvider {
        return if (gui.tab == tab) activeItem else inactiveItem
    }

    override fun handleClick(clickType: ClickType, player: Player, click: Click) {
        if (clickType == ClickType.LEFT && gui.isTabAvailable(tab) && gui.tab != tab) {
            player.playClickSound()
            gui.tab = tab
        }
    }
}
