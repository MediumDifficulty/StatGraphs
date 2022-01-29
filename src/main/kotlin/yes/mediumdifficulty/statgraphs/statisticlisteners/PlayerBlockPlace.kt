package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerBlockPlace: AbstractStatisticListener(), Listener {
    override val name = "place"
    override val relativeStatisticPath = "place"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.BLOCKS.specifiers

    @EventHandler
    fun blockPlace(e: BlockPlaceEvent) {
        incrementPlayer(e.player, e.block.type.name, 1)
    }
}