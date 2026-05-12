package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.world.item.NovaItem

@Init(stage = InitStage.PRE_PACK)
object GuiItems {

    val TAB_BTN_BOTTOM = guiItem("tab_btn_bottom")
    val TAB_BTN_BOTTOM_ACTIVE = guiItem("tab_btn_bottom_active")
    val TAB_BTN_BOTTOM_CONNECTED = guiItem("tab_btn_bottom_connected")
    val TAB_BTN_BOTTOM_CONNECTED_ACTIVE = guiItem("tab_btn_bottom_connected_active")
    val TAB_BTN_MIDDLE = guiItem("tab_btn_middle")
    val TAB_BTN_MIDDLE_ACTIVE = guiItem("tab_btn_middle_active")
    val TAB_BTN_TOP = guiItem("tab_btn_top")
    val TAB_BTN_TOP_ACTIVE = guiItem("tab_btn_top_active")
    val TAB_BTN_TOP_CONNECTED = guiItem("tab_btn_top_connected")
    val TAB_BTN_TOP_CONNECTED_ACTIVE = guiItem("tab_btn_top_connected_active")

    private fun guiItem(name: String, localizedName: String = ""): NovaItem = IronChests.item("gui/opaque/$name") {
        localizedName(localizedName)
        hidden(true)

        modelDefinition {
            model = buildModel {
                createGuiModel(background = false, stretched = true, "item/gui/$name")
            }
        }
    }

}