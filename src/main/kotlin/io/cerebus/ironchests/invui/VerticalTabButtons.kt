package io.cerebus.ironchests.invui

import io.cerebus.ironchests.registry.GuiItems
import xyz.xenondevs.invui.item.Item

object VerticalTabButtons {
    
    fun createTabBtnBottomSectionItem(tab: Int, bottom: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (bottom) GuiItems.TAB_BTN_BOTTOM_ACTIVE.createClientsideItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED_ACTIVE.createClientsideItemBuilder(),
            if (bottom) GuiItems.TAB_BTN_BOTTOM.createClientsideItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED.createClientsideItemBuilder())
    }
    
    fun createTabBtnMiddleSectionItem(tab: Int): Item {
        return SimpleTabItem(
            tab,
            GuiItems.TAB_BTN_MIDDLE_ACTIVE.createClientsideItemBuilder(),
            GuiItems.TAB_BTN_MIDDLE.createClientsideItemBuilder())
    }
    
    fun createTabBtnTopSectionItem(tab: Int, top: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (top) GuiItems.TAB_BTN_TOP_ACTIVE.createClientsideItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED_ACTIVE.createClientsideItemBuilder(),
            if (top) GuiItems.TAB_BTN_TOP.createClientsideItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED.createClientsideItemBuilder())
    }
    
}