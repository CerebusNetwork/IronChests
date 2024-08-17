package io.cerebus.ironchests.tileentity

import io.cerebus.ironchests.invui.MultiItem
import io.cerebus.ironchests.invui.VerticalTabButtons
import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.gui.TabGui
import xyz.xenondevs.invui.gui.structure.Markers
import xyz.xenondevs.invui.item.Item
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.menu.TileEntityMenuClass

class GoldChest(pos: BlockPos, blockState: NovaBlockState, data: Compound) : CustomChest(pos, blockState, data) {
    
    init {
        containers = arrayOf(storedInventory("goldChestInventory1", 48), storedInventory("goldChestInventory2", 48))
    }
    
    @TileEntityMenuClass
    inner class GoldChestMenu : GlobalTileEntityMenu() {
        
        private val tabGui1 = Gui.empty(8, 6)
        private val tabGui2 = Gui.empty(8, 6)
        
        init {
            tabGui1.fillRectangle(0, 0, 8, containers[0], true)
            tabGui2.fillRectangle(0, 0, 8, containers[1], true)
        }
        
        private fun createTabItems(tab: Int, top: Boolean = false, bottom: Boolean = false): Array<Item> {
            return arrayOf(
                VerticalTabButtons.createTabBtnTopSectionItem(tab, top),
                VerticalTabButtons.createTabBtnMiddleSectionItem(tab),
                VerticalTabButtons.createTabBtnBottomSectionItem(tab, bottom))
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
    
}