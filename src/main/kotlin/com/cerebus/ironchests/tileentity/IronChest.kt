package com.cerebus.ironchests.tileentity

import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.tileentity.TileEntity
import xyz.xenondevs.nova.tileentity.menu.TileEntityMenuClass

class IronChest(blockState: NovaTileEntityState) : TileEntity(blockState) {
    
    private val inventory = getInventory("ironChestInventory", 54)

    @TileEntityMenuClass
    inner class IronChestMenu : GlobalTileEntityMenu() {
        
        override val gui: Gui = Gui.empty(9, 6)
        
        init {
            gui.fillRectangle(0, 0, 9, inventory, true)
        }
        
    }
    
    
}