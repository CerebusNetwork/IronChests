package io.cerebus.ironchests.invui

import io.cerebus.ironchests.registry.GuiItems
import xyz.xenondevs.invui.item.Item

object VerticalTabButtons {

    fun createTabBtnBottomSectionItem(tab: Int, bottom: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (bottom) GuiItems.TAB_BTN_BOTTOM_ACTIVE.clientsideProvider else GuiItems.TAB_BTN_BOTTOM_CONNECTED_ACTIVE.clientsideProvider,
            if (bottom) GuiItems.TAB_BTN_BOTTOM.clientsideProvider else GuiItems.TAB_BTN_BOTTOM_CONNECTED.clientsideProvider)
    }

    fun createTabBtnMiddleSectionItem(tab: Int): Item {
        return SimpleTabItem(
            tab,
            GuiItems.TAB_BTN_MIDDLE_ACTIVE.clientsideProvider,
            GuiItems.TAB_BTN_MIDDLE.clientsideProvider)
    }

    fun createTabBtnTopSectionItem(tab: Int, top: Boolean): Item {
        return SimpleTabItem(
            tab,
            if (top) GuiItems.TAB_BTN_TOP_ACTIVE.clientsideProvider else GuiItems.TAB_BTN_TOP_CONNECTED_ACTIVE.clientsideProvider,
            if (top) GuiItems.TAB_BTN_TOP.clientsideProvider else GuiItems.TAB_BTN_TOP_CONNECTED.clientsideProvider)
    }

}
