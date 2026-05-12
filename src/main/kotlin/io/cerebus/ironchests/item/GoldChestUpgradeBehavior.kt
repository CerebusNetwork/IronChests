package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Blocks
import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.DiamondChest
import io.cerebus.ironchests.tileentity.GoldChest
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.inventory.event.UpdateReason
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.format.WorldDataManager
import xyz.xenondevs.nova.world.pos

object GoldChestUpgradeBehavior : BaseUpgradeBehaviour(Blocks.DIAMOND_CHEST) {
    override fun isValidTargetBlock(block: Block): Boolean = (block.novaBlock?.item == Items.GOLD_CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val goldChest = (WorldDataManager.getTileEntity(block.pos) as? GoldChest)!!
        
        return ChestData(block.novaBlockState?.get(DefaultBlockStateProperties.FACING)!!, arrayOf(goldChest.containers[0].items, goldChest.containers[1].items))
    }

    override fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>) {
        val diamondChest = (WorldDataManager.getTileEntity(blockLocation.pos) as? DiamondChest)!!
        
        for (i in items.indices) {
            for (itemIndex in items[i].indices) {
                diamondChest.containers[i].setItem(UpdateReason.SUPPRESSED, itemIndex, items[i][itemIndex])
            }
        }
    }
    
}