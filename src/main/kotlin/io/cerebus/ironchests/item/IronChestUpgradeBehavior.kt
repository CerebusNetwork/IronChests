package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.nova.data.world.block.property.Directional
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.item.behavior.ItemBehavior
import xyz.xenondevs.nova.player.WrappedPlayerInteractEvent
import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.util.place
import xyz.xenondevs.nova.world.block.context.BlockPlaceContext
import xyz.xenondevs.nova.world.pos
import org.bukkit.block.data.type.Chest as ChestBlockData

object IronChestUpgradeBehavior : ItemBehavior {
    
    override fun handleInteract(player: Player, itemStack: ItemStack, action: Action, event: WrappedPlayerInteractEvent) {
        if (event.event.action != Action.RIGHT_CLICK_BLOCK || player.isSneaking) {
            return
        }
        val hand = event.event.hand!!
        val block = event.event.clickedBlock!!
        val blockLocation = block.location
        
        if (block.novaBlock?.item != Items.IRON_CHEST) {
            return
        }
        
        val novaBlockState = blockLocation.block.novaBlockState
        val ironChest = ((novaBlockState as? NovaTileEntityState)?.tileEntity as? IronChest)!!
        val items = ironChest.inventories[0].items
        
        val placePos = block.location.pos
        val direction = novaBlockState.getProperty(Directional::class)!!.facing
        val directionalSourceLocation = block.getRelative(direction).location.center().setDirection(
            direction.oppositeFace.let {
                Vector(it.modX, it.modY, it.modZ)
            })
        
        block.type = Material.AIR
        val ctx = BlockPlaceContext(placePos, Items.GOLD_CHEST.createItemStack(), player, directionalSourceLocation, player.uniqueId, placePos.copy(y = placePos.y - 1), BlockFace.UP)
        if (placePos.block.place(ctx)) {
            val goldChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? GoldChest)!!
            
            for (i in items.indices) {
                val x = i % 9
                val y = i / 9
                val targetInventory = x / 8
                val targetSlot = y * 8 + (x % 8)
                goldChest.inventories[targetInventory].setItem(null, targetSlot, items[i])
            }
            
            player.inventory.getItem(hand).amount -= 1
            
            event.event.isCancelled = true
        }
    }
}