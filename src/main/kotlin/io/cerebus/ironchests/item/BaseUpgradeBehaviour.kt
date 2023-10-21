package io.cerebus.ironchests.item

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.nova.item.behavior.ItemBehavior
import xyz.xenondevs.nova.player.WrappedPlayerInteractEvent
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.place
import xyz.xenondevs.nova.world.block.context.BlockPlaceContext
import xyz.xenondevs.nova.world.pos

abstract class BaseUpgradeBehaviour : ItemBehavior {
    
    abstract fun isValidTargetBlock(block: Block): Boolean
    
    abstract fun getOriginalChestData(block: Block): ChestData
    
    abstract fun createUpgradedChestItemStack(): ItemStack
    
    abstract fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>)
    
    override fun handleInteract(player: Player, itemStack: ItemStack, action: Action, wrappedEvent: WrappedPlayerInteractEvent) {
        if (wrappedEvent.event.action != Action.RIGHT_CLICK_BLOCK || player.isSneaking) {
            return
        }
        
        val hand = wrappedEvent.event.hand!!
        val block = wrappedEvent.event.clickedBlock!!
        val blockLocation = block.location
        
        if (!isValidTargetBlock(block)) {
            return
        }
        
        val originalChestData = getOriginalChestData(block)
        
        val placePos = block.location.pos
        val directionalSourceLocation = block.getRelative(originalChestData.direction).location.center().setDirection(
            originalChestData.direction.oppositeFace.let {
                Vector(it.modX, it.modY, it.modZ)
            })
        
        block.type = Material.AIR
        val ctx = BlockPlaceContext(placePos, createUpgradedChestItemStack(), player, directionalSourceLocation, player.uniqueId, placePos.copy(y = placePos.y - 1), BlockFace.UP)
        if (placePos.block.place(ctx)) {
            setUpgradedChestItems(blockLocation, originalChestData.items)
            
            player.inventory.getItem(hand).amount -= 1
            
            wrappedEvent.event.isCancelled = true
        } else {
            //TODO: drop items as fallback
        }
    }
    
    data class ChestData(val direction: BlockFace, val items: Array<Array<ItemStack?>>)
}