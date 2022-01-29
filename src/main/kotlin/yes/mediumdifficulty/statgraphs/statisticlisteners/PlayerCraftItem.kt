package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerCraftItem: AbstractStatisticListener(), Listener {
    override val name = "craft"
    override val relativeStatisticPath = "craft"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.ITEMS.specifiers

    @EventHandler
    fun onCraft(e: CraftItemEvent) {
        if(e.whoClicked !is Player) return
        if(e.currentItem == null) return

        incrementPlayer(e.whoClicked as Player, e.currentItem!!.type.name, 1)
    }
}