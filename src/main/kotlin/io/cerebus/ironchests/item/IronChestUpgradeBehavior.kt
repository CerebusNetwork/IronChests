package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Blocks
import io.cerebus.ironchests.registry.Items
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.inventory.event.UpdateReason
import xyz.xenondevs.nova.util.novaBlock
import xyz.xenondevs.nova.util.novaBlockState
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.format.WorldDataManager
import xyz.xenondevs.nova.world.pos

object IronChestUpgradeBehavior : BaseUpgradeBehaviour(Blocks.GOLD_CHEST) {

    override fun isValidTargetBlock(block: Block): Boolean = (block.novaBlock?.item == Items.IRON_CHEST)
    
    override fun getOriginalChestData(block: Block): ChestData {
        val novaBlockState = block.novaBlockState
        val ironChest = (WorldDataManager.getTileEntity(block.pos) as? IronChest)!!
        
        return ChestData(block.novaBlockState?.get(DefaultBlockStateProperties.FACING)!!, arrayOf(ironChest.containers[0].items))
    }

    override fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>) {
        val goldChest = (WorldDataManager.getTileEntity(blockLocation.pos) as? GoldChest)!!
        
        for (i in items[0].indices) {
            val x = i % 9
            val y = i / 9
            val targetContainer = x / 8
            val targetSlot = y * 8 + (x % 8)
            goldChest.containers[targetContainer].setItem(UpdateReason.SUPPRESSED, targetSlot, items[0][i])
        }
    }
    
}