package io.cerebus.ironchests.registry

import io.cerebus.ironchests.IronChests
import io.cerebus.ironchests.tileentity.DiamondChest
import io.cerebus.ironchests.tileentity.GoldChest
import io.cerebus.ironchests.tileentity.IronChest
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import org.bukkit.Material
import xyz.xenondevs.nova.initialize.Init
import xyz.xenondevs.nova.initialize.InitStage
import xyz.xenondevs.nova.util.nmsDirection
import xyz.xenondevs.nova.world.block.NovaTileEntityBlock
import xyz.xenondevs.nova.world.block.TileEntityConstructor
import xyz.xenondevs.nova.world.block.behavior.*
import xyz.xenondevs.nova.world.block.sound.SoundGroup
import xyz.xenondevs.nova.world.block.state.property.DefaultBlockStateProperties
import xyz.xenondevs.nova.world.block.state.property.DefaultScopedBlockStateProperties
import xyz.xenondevs.nova.world.item.tool.VanillaToolCategories
import xyz.xenondevs.nova.world.item.tool.VanillaToolTiers

@Init(stage = InitStage.PRE_PACK)
object Blocks {

    val IRON_CHEST = entityCustomChest(
        "iron_chest", ::IronChest,
        Breakable(
            3.0,
            setOf(VanillaToolCategories.PICKAXE),
            VanillaToolTiers.STONE,
            true,
            Material.IRON_BLOCK
        )
    )
    val GOLD_CHEST = entityCustomChest(
        "gold_chest", ::GoldChest,
        Breakable(
            3.0,
            setOf(VanillaToolCategories.PICKAXE),
            VanillaToolTiers.STONE,
            true,
            Material.GOLD_BLOCK
        )
    )
    val DIAMOND_CHEST = entityCustomChest(
        "diamond_chest", ::DiamondChest,
        Breakable(
            3.0,
            setOf(VanillaToolCategories.PICKAXE),
            VanillaToolTiers.STONE,
            true,
            Material.DIAMOND_BLOCK
        )
    )

    private fun entityCustomChest(
        name: String,
        ctor: TileEntityConstructor,
        breakable: BlockBehaviorHolder
    ): NovaTileEntityBlock = IronChests.tileEntity(name, ctor) {
        stateProperties(DefaultScopedBlockStateProperties.FACING_HORIZONTAL)
        behaviors(
            TileEntityLimited,
            TileEntityDrops,
            TileEntityInteractive,
            breakable,
            BlockSounds(SoundGroup.STONE)
        )
        entityBacked({
            val facing = getPropertyValueOrThrow(DefaultBlockStateProperties.FACING)
            Blocks.CHEST.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing.nmsDirection)
        }, {
            defaultModel.rotated()
        })
    }

}
