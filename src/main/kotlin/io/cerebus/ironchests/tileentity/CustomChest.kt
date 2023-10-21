package io.cerebus.ironchests.tileentity

import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.tileentity.TileEntity

open class CustomChest(blockState: NovaTileEntityState) : TileEntity(blockState) {
    lateinit var containers: Array<VirtualInventory>
}