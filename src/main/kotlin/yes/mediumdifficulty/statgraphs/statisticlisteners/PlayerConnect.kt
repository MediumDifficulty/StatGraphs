package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerConnect: AbstractStatisticListener(), Listener {
    override val name = "logins"
    override val relativeStatisticPath = "logins"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    @EventHandler
    fun onConnect(e: PlayerJoinEvent) {
        incrementPlayer(e.player, null, 1)
    }
}