package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.DiamondChest
import io.cerebus.ironchests.tileentity.GoldChest
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.data.world.block.property.Directional
import xyz.xenondevs.nova.data.world.block.state.NovaTileEntityState
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.novaBlockState

object GoldChestUpgradeBehavior : BaseUpgradeBehaviour() {
    override fun isValidTargetBlock(block: Block): Boolean = (block.novaBlock?.item == Items.GOLD_CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val novaBlockState = block.novaBlockState
        val goldChest = ((novaBlockState as? NovaTileEntityState)?.tileEntity as? GoldChest)!!
        
        return ChestData(novaBlockState.getProperty(Directional::class)!!.facing, arrayOf(goldChest.containers[0].items, goldChest.containers[1].items))
    }
    
    override fun createUpgradedChestItemStack(): ItemStack = Items.DIAMOND_CHEST.createItemStack()
    
    override fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>) {
        val diamondChest = ((blockLocation.block.novaBlockState as? NovaTileEntityState)?.tileEntity as? DiamondChest)!!
        
        for (i in items.indices) {
            for (itemIndex in items[i].indices) {
                diamondChest.containers[i].setItemSilently(itemIndex, items[i][itemIndex])
            }
        }
    }
    
}