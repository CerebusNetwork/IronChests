package io.cerebus.ironchests.item

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.nova.context.Context
import xyz.xenondevs.nova.context.intention.BlockInteract
import xyz.xenondevs.nova.context.intention.BlockPlace
import xyz.xenondevs.nova.util.BlockUtils
import xyz.xenondevs.nova.world.InteractionResult
import xyz.xenondevs.nova.world.block.NovaBlock
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.item.ItemAction
import xyz.xenondevs.nova.world.item.behavior.ItemBehavior
import xyz.xenondevs.nova.world.pos

abstract class BaseUpgradeBehaviour(val upgradedNovaBlock: NovaBlock) : ItemBehavior {

    abstract fun isValidTargetBlock(block: Block): Boolean

    abstract fun getOriginalChestData(block: Block): ChestData

    abstract fun setUpgradedChestItems(blockLocation: Location, items: Array<Array<ItemStack?>>)

    override fun useOnBlock(itemStack: ItemStack, block: Block, ctx: Context<BlockInteract>): InteractionResult {
        if (!isValidTargetBlock(block))
            return InteractionResult.Pass

        val blockLocation = block.location
        val originalChestData = getOriginalChestData(block)
        val placePos = blockLocation.pos

        block.type = Material.AIR

        val placeCtx = Context.intention(BlockPlace)
            .param(BlockPlace.BLOCK_POS, placePos)
            .param(BlockPlace.BLOCK_TYPE_NOVA, upgradedNovaBlock)
            .param(BlockPlace.BLOCK_STATE_NOVA, upgradedNovaBlock.defaultBlockState.with(DefaultBlockStateProperties.FACING, originalChestData.direction))
            .build()

        BlockUtils.placeBlock(placeCtx)
        setUpgradedChestItems(blockLocation, originalChestData.items)

        return InteractionResult.Success(swing = true, action = ItemAction.Consume())
    }

    data class ChestData(val direction: BlockFace, val items: Array<Array<ItemStack?>>)
}
