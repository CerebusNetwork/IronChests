package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Material
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
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.util.place
import xyz.xenondevs.nova.world.block.context.BlockPlaceContext
import xyz.xenondevs.nova.world.pos

object WoodChestUpgradeBehavior : ItemBehavior() {
    
    override fun handleInteract(player: Player, itemStack: ItemStack, action: Action, event: PlayerInteractEvent) {
        if (event.action != Action.RIGHT_CLICK_BLOCK || player.isSneaking) {
            return
        }
        val hand = event.hand!!
        val block = event.clickedBlock!!
        val blockLocation = block.location
        
        if (block.type != Material.CHEST) {
            return
        }
        
        val chestInventory = (block.world.getBlockState(blockLocation) as Chest).inventory
        val actualBlockInventory =
            if (chestInventory is DoubleChestInventory) {
                val leftPos = (chestInventory.leftSide.holder as? BlockInventoryHolder)!!.block.pos
                if (leftPos == blockLocation.pos) chestInventory.leftSide else chestInventory.rightSide
            } else {
                chestInventory
            }
        val items = actualBlockInventory.contents!!
        
        val placePos = block.location.pos
        val direction = (block.blockData as org.bukkit.block.data.type.Chest).facing
        val directionalSourceLocation = block.getRelative(direction).location.center().setDirection(
            direction.oppositeFace.let {
                Vector(it.modX, it.modY, it.modZ)
            })
        
        block.type = Material.AIR
        val ctx = BlockPlaceContext(placePos, Items.IRON_CHEST.createItemStack(), player, directionalSourceLocation, player.uniqueId, placePos.copy(y = placePos.y - 1), BlockFace.UP)
        if (placePos.block.place(ctx)) {
            val ironChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? IronChest)!!
            
            for (i in items.indices) {
                ironChest.inventories[0].setItem(null, i, items[i])
            }
            
            player.inventory.getItem(hand)!!.amount -= 1
            
            event.isCancelled = true
        }
    }
}