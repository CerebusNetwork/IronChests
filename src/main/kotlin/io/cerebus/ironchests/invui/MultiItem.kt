package io.cerebus.ironchests.invui

import xyz.xenondevs.invui.item.Item
import java.util.function.Supplier

class MultiItem(private val items: Array<Item>) : Supplier<Item> {
    
    private var i = 0
    
    override fun get(): Item {
        val item = items[i]
        i = (i + 1).mod(items.size)
        return item
    }
    
}