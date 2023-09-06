package io.cerebus.ironchests.invui

import xyz.xenondevs.invui.gui.TabGui
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.controlitem.TabItem

class SimpleTabItem(private val tab: Int, private val activeItem: ItemBuilder, private val inactiveItem: ItemBuilder) : TabItem(tab) {
    
    override fun getItemProvider(gui: TabGui): ItemProvider {
        return if (gui.currentTab == tab) activeItem else inactiveItem
    }
    
}