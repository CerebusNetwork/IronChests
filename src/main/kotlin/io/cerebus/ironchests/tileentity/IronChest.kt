package io.cerebus.ironchests.tileentity

import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.menu.TileEntityMenuClass


class IronChest(pos: BlockPos, blockState: NovaBlockState, data: Compound) : CustomChest(pos, blockState, data) {
    
    init {
        containers = arrayOf(storedInventory("ironChestInventory", 54))
    }

    @TileEntityMenuClass
    inner class IronChestMenu : GlobalTileEntityMenu() {
        
        override val gui: Gui = Gui.empty(9, 6)
        
        init {
            gui.fillRectangle(0, 0, 9, containers[0], true)
        }
        
    }
    
    
}