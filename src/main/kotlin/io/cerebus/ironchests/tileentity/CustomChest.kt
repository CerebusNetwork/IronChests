package io.cerebus.ironchests.tileentity

import xyz.xenondevs.cbf.Compound
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.nova.world.BlockPos
import xyz.xenondevs.nova.world.block.state.NovaBlockState
import xyz.xenondevs.nova.world.block.tileentity.TileEntity

open class CustomChest(pos: BlockPos, blockState: NovaBlockState, data: Compound) : TileEntity(pos, blockState, data) {
    lateinit var containers: Array<VirtualInventory>
}