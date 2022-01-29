package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerDisconnect: AbstractStatisticListener(), Listener {
    override val name = "disconnects"
    override val relativeStatisticPath = "disconnects"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    @EventHandler
    fun onLeave(e: PlayerQuitEvent) {
        incrementPlayer(e.player, null, 1)
    }
}