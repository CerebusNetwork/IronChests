package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.Chest
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.BlockInventoryHolder
import org.bukkit.inventory.DoubleChestInventory
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.item.behavior.ItemBehavior
import xyz.xenondevs.nova.player.WrappedPlayerInteractEvent
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.util.place
import xyz.xenondevs.nova.world.block.context.BlockPlaceContext
import xyz.xenondevs.nova.world.pos
import org.bukkit.block.data.type.Chest as ChestBlockData

object WoodChestUpgradeBehavior : BaseUpgradeBehaviour() {
    override fun isValidTargetBlock(block: Block): Boolean = (block.type == Material.CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val chestInventory = (block.world.getBlockState(block.location) as Chest).inventory
        val actualBlockInventory =
            if (chestInventory is DoubleChestInventory) {
                val leftPos = (chestInventory.leftSide.holder as? BlockInventoryHolder)!!.block.pos
                if (leftPos == block.location.pos) chestInventory.leftSide else chestInventory.rightSide
            } else {
                chestInventory
            }
        return ChestData((block.blockData as ChestBlockData).facing, actualBlockInventory.contents)
    }
    
    override fun createUpgradedChestItemStack(): ItemStack = Items.IRON_CHEST.createItemStack()
    
    override fun setUpgradedChestItems(blockLocation: Location, items: Array<ItemStack?>) {
        val ironChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? IronChest)!!
        
        for (i in items.indices) {
            ironChest.containers[0].setItem(null, i, items[i])
        }
    }
}