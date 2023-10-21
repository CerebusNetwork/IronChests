package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
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

object IronChestUpgradeBehavior : BaseUpgradeBehaviour() {
    override fun isValidTargetBlock(block: Block): Boolean = (block.novaBlock?.item == Items.IRON_CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val novaBlockState = block.novaBlockState
        val ironChest = ((novaBlockState as? NovaTileEntityState)?.tileEntity as? IronChest)!!
        
        return ChestData(novaBlockState.getProperty(Directional::class)!!.facing, ironChest.containers[0].items)
    }
    
    override fun createUpgradedChestItemStack(): ItemStack = Items.GOLD_CHEST.createItemStack()
    
    override fun setUpgradedChestItems(blockLocation: Location, items: Array<ItemStack?>) {
        val goldChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? GoldChest)!!
        
        for (i in items.indices) {
            val x = i % 9
            val y = i / 9
            val targetContainer = x / 8
            val targetSlot = y * 8 + (x % 8)
            goldChest.containers[targetContainer].setItem(null, targetSlot, items[i])
        }
    }
    
}