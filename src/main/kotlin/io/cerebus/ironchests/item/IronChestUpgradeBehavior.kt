package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.data.world.block.property.Directional
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.novaBlockState

object IronChestUpgradeBehavior : BaseUpgradeBehaviour() {
    override fun isValidTargetBlock(block: Block): Boolean = (block.novaBlock?.item == Items.IRON_CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val novaBlockState = block.novaBlockState
        val ironChest = ((novaBlockState as? NovaTileEntityState)?.tileEntity as? IronChest)!!
        
        return ChestData(novaBlockState.getProperty(Directional::class)!!.facing, arrayOf(ironChest.containers[0].items))
    }
    
    override fun createUpgradedChestItemStack(): ItemStack = Items.GOLD_CHEST.createItemStack()
    
    override fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>) {
        val goldChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? GoldChest)!!
        
        for (i in items[0].indices) {
            val x = i % 9
            val y = i / 9
            val targetContainer = x / 8
            val targetSlot = y * 8 + (x % 8)
            goldChest.containers[targetContainer].setItemSilently(targetSlot, items[0][i])
        }
    }
    
}