package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerDeath: AbstractStatisticListener(), Listener {
    override val name = "deaths"
    override val relativeStatisticPath = "deaths"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        incrementPlayer(e.entity, null, 1)
    }
}