package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Blocks
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.Chest
import org.bukkit.inventory.BlockInventoryHolder
import org.bukkit.inventory.DoubleChestInventory
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.inventory.event.UpdateReason
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.world.format.WorldDataManager
import xyz.xenondevs.nova.world.pos
import org.bukkit.block.data.type.Chest as ChestBlockData

object WoodChestUpgradeBehavior : BaseUpgradeBehaviour(Blocks.IRON_CHEST) {

    override fun isValidTargetBlock(block: Block): Boolean = (block.type == Material.CHEST && block.novaBlock == null)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val chestInventory = (block.world.getBlockState(block.location) as Chest).inventory
        val actualBlockInventory =
            if (chestInventory is DoubleChestInventory) {
                val leftPos = (chestInventory.leftSide.holder as? BlockInventoryHolder)!!.block.pos
                if (leftPos == block.location.pos) chestInventory.leftSide else chestInventory.rightSide
            } else {
                chestInventory
            }
        return ChestData((block.blockData as ChestBlockData).facing, arrayOf(actualBlockInventory.contents))
    }
    
    override fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>) {
        val ironChest = (WorldDataManager.getTileEntity(blockLocation.pos) as? IronChest)!!

        for (i in items[0].indices) {
            ironChest.containers[0].setItem(UpdateReason.SUPPRESSED, i, items[0][i])
        }
    }
}