package io.cerebus.ironchests.tileentity

import io.cerebus.ironchests.invui.MultiItem
import io.cerebus.ironchests.invui.VerticalTabButtons
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.TabGui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.tileentity.TileEntity
import xyz.xenondevs.nova.tileentity.menu.TileEntityMenuClass

class DiamondChest(blockState: NovaTileEntityState) : TileEntity(blockState) {
    
    private val inventory1 = getInventory("goldChestInventory1", 48)
    private val inventory2 = getInventory("goldChestInventory2", 48)
    private val inventory3 = getInventory("goldChestInventory3", 48)
    
    @TileEntityMenuClass
    inner class GoldChestMenu : GlobalTileEntityMenu() {
        
        private val tabGui1 = Gui.empty(8, 6)
        private val tabGui2 = Gui.empty(8, 6)
        private val tabGui3 = Gui.empty(8, 6)
        
        init {
            tabGui1.fillRectangle(0, 0, 8, inventory1, true)
            tabGui2.fillRectangle(0, 0, 8, inventory2, true)
            tabGui3.fillRectangle(0, 0, 8, inventory3, true)
        }
        
        private fun createTabItems(tab: Int, top: Boolean = false, bottom: Boolean = false): Array<Item> {
            return arrayOf(
                VerticalTabButtons.createTabBtnTopSectionItem(tab, top),
                VerticalTabButtons.createTabBtnBottomSectionItem(tab, bottom))
        }
        
        override val gui: Gui = TabGui.normal()
            .setStructure(
                "x x x x x x x x 0",
                "x x x x x x x x 0",
                "x x x x x x x x 1",
                "x x x x x x x x 1",
                "x x x x x x x x 2",
                "x x x x x x x x 2")
            .addIngredient('x', Markers.CONTENT_LIST_SLOT_HORIZONTAL)
            .addIngredient('0', MultiItem(createTabItems(0, top = true)))
            .addIngredient('1', MultiItem(createTabItems(1)))
            .addIngredient('2', MultiItem(createTabItems(2, bottom = true)))
            .setTabs(listOf(tabGui1, tabGui2, tabGui3))
            .build()
        
    }
    
}