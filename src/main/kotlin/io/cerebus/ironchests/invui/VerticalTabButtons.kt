package io.cerebus.ironchests.invui

import io.cerebus.ironchests.registry.GuiItems
import xyz.xenondevs.invui.item.Item

object VerticalTabButtons {
    
    fun createTabBtnBottomSectionItem(tab: Int, bottom: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (bottom) GuiItems.TAB_BTN_BOTTOM_ACTIVE.createItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED_ACTIVE.createItemBuilder(),
            if (bottom) GuiItems.TAB_BTN_BOTTOM.createItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED.createItemBuilder())
    }
    
    fun createTabBtnMiddleSectionItem(tab: Int): Item {
        return SimpleTabItem(
            tab,
            GuiItems.TAB_BTN_MIDDLE_ACTIVE.createItemBuilder(),
            GuiItems.TAB_BTN_MIDDLE.createItemBuilder())
    }
    
    fun createTabBtnTopSectionItem(tab: Int, top: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (top) GuiItems.TAB_BTN_TOP_ACTIVE.createItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED_ACTIVE.createItemBuilder(),
            if (top) GuiItems.TAB_BTN_TOP.createItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED.createItemBuilder())
    }
    
}