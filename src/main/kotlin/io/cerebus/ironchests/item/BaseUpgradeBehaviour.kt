package io.cerebus.ironchests.item

import io.cerebus.ironchests.registry.Blocks
import net.minecraft.world.item.context.BlockPlaceContext
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.DefaultContextIntentions
import xyz.xenondevs.nova.context.param.DefaultContextParamTypes
import xyz.xenondevs.nova.util.BlockUtils

import xyz.xenondevs.nova.util.center
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.behavior.ItemBehavior
import xyz.xenondevs.nova.world.player.WrappedPlayerInteractEvent
import xyz.xenondevs.nova.world.pos

abstract class BaseUpgradeBehaviour(val upgradedNovaBlock: NovaBlock) : ItemBehavior {
    
    abstract fun isValidTargetBlock(block: Block): Boolean
    
    abstract fun getOriginalChestData(block: Block): ChestData
    
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
        
        block.type = Material.AIR

        val ctx = Context.intention(DefaultContextIntentions.BlockPlace)
            .param(DefaultContextParamTypes.BLOCK_POS, placePos)
            .param(DefaultContextParamTypes.BLOCK_TYPE_NOVA, upgradedNovaBlock)
            .param(DefaultContextParamTypes.BLOCK_STATE_NOVA, upgradedNovaBlock.defaultBlockState.with(DefaultBlockStateProperties.FACING, originalChestData.direction))
            .build()

        BlockUtils.placeBlock(ctx)
        setUpgradedChestItems(blockLocation, originalChestData.items)
        player.inventory.getItem(hand).amount -= 1
        wrappedEvent.event.isCancelled = true
    }
    
    data class ChestData(val direction: BlockFace, val items: Array<Array<ItemStack?>>)
}