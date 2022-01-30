package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.PlayerUUIDManager
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class ServerNewPlayers: AbstractStatisticListener(), Listener {
    override val name = "new_players"
    override val relativeStatisticPath = "newplayers"
    override val statisticType = StatisticType.SERVER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        if(!PlayerUUIDManager.UUIDs.contains(e.player.uniqueId))
            incrementServer(null, 1)
    }
}