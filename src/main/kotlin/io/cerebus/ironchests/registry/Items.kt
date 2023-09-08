package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import io.cerebus.ironchests.item.WoodChestUpgradeBehavior
import xyz.xenondevs.nova.addon.registry.ItemRegistry
import xyz.xenondevs.nova.initialize.Init

@Init
object Items : ItemRegistry by IronChests.registry {
    
    val IRON_CHEST = registerItem(Blocks.IRON_CHEST)
    val GOLD_CHEST = registerItem(Blocks.GOLD_CHEST)
    val DIAMOND_CHEST = registerItem(Blocks.DIAMOND_CHEST)
    
    val WOOD_IRON_UPGRADE = item("wood_iron_upgrade").behaviors(WoodChestUpgradeBehavior).maxStackSize(1).register()
    
}
@Init
object GuiItems : ItemRegistry by IronChests.registry {
    
    val TAB_BTN_BOTTOM = registerUnnamedHiddenItem("gui_tab_btn_bottom")
    val TAB_BTN_BOTTOM_ACTIVE = registerUnnamedHiddenItem("gui_tab_btn_bottom_active")
    val TAB_BTN_BOTTOM_CONNECTED = registerUnnamedHiddenItem("gui_tab_btn_bottom_connected")
    val TAB_BTN_BOTTOM_CONNECTED_ACTIVE = registerUnnamedHiddenItem("gui_tab_btn_bottom_connected_active")
    val TAB_BTN_MIDDLE = registerUnnamedHiddenItem("gui_tab_btn_middle")
    val TAB_BTN_MIDDLE_ACTIVE = registerUnnamedHiddenItem("gui_tab_btn_middle_active")
    val TAB_BTN_TOP = registerUnnamedHiddenItem("gui_tab_btn_top")
    val TAB_BTN_TOP_ACTIVE = registerUnnamedHiddenItem("gui_tab_btn_top_active")
    val TAB_BTN_TOP_CONNECTED = registerUnnamedHiddenItem("gui_tab_btn_top_connected")
    val TAB_BTN_TOP_CONNECTED_ACTIVE = registerUnnamedHiddenItem("gui_tab_btn_top_connected_active")
    
}