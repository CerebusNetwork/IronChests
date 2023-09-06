package io.cerebus.ironchests.tileentity

import io.cerebus.ironchests.invui.MultiItem
import io.cerebus.ironchests.registry.GuiItems
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.TabGui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.invui.item.ItemProvider
import xyz.xenondevs.invui.item.builder.ItemBuilder
import xyz.xenondevs.invui.item.impl.controlitem.TabItem
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.tileentity.TileEntity
import xyz.xenondevs.nova.tileentity.menu.TileEntityMenuClass

class GoldChest(blockState: NovaTileEntityState) : TileEntity(blockState) {
    
    private val inventory1 = getInventory("goldChestInventory1", 48)
    private val inventory2 = getInventory("goldChestInventory2", 48)
    
    @TileEntityMenuClass
    inner class GoldChestMenu : GlobalTileEntityMenu() {
        
        private val tabGui1 = Gui.empty(8, 6)
        private val tabGui2 = Gui.empty(8, 6)
        
        init {
            tabGui1.fillRectangle(0, 0, 8, inventory1, true)
            tabGui2.fillRectangle(0, 0, 8, inventory2, true)
        }
        
        private fun createTabItems(tab: Int, top: Boolean = false, bottom: Boolean = false): Array<Item> {
            return arrayOf(
                MyTabItem(tab,
                    if (top) GuiItems.TAB_BTN_TOP_ACTIVE.createClientsideItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED_ACTIVE.createClientsideItemBuilder(),
                    if (top) GuiItems.TAB_BTN_TOP.createClientsideItemBuilder() else GuiItems.TAB_BTN_TOP_CONNECTED.createClientsideItemBuilder()),
                MyTabItem(tab,
                    GuiItems.TAB_BTN_MIDDLE_ACTIVE.createClientsideItemBuilder(),
                    GuiItems.TAB_BTN_MIDDLE.createClientsideItemBuilder()),
                MyTabItem(tab,
                    if (bottom) GuiItems.TAB_BTN_BOTTOM_ACTIVE.createClientsideItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED_ACTIVE.createClientsideItemBuilder(),
                    if (bottom) GuiItems.TAB_BTN_BOTTOM.createClientsideItemBuilder() else GuiItems.TAB_BTN_BOTTOM_CONNECTED.createClientsideItemBuilder()))
        }
        
        override val gui: Gui = TabGui.normal()
            .setStructure(
                "x x x x x x x x 0",
                "x x x x x x x x 0",
                "x x x x x x x x 0",
                "x x x x x x x x 1",
                "x x x x x x x x 1",
                "x x x x x x x x 1")
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
            .addIngredient('0', MultiItem(createTabItems(0, top = true)))
            .addIngredient('1', MultiItem(createTabItems(1, bottom = true)))
            .setTabs(listOf(tabGui1, tabGui2))
            .build()
        
    }
    
    class MyTabItem(private val tab: Int, private val activeItem: ItemBuilder, private val inactiveItem: ItemBuilder) : TabItem(tab) {
        
        override fun getItemProvider(gui: TabGui): ItemProvider {
            return if (gui.currentTab == tab) {
                activeItem
            } else {
                inactiveItem
            }
        }
        
    }
    
    
}