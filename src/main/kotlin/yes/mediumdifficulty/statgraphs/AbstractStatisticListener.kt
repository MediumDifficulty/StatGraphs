package yes.mediumdifficulty.statgraphs

import org.bukkit.OfflinePlayer

abstract class AbstractStatisticListener {
    abstract val name: String
    abstract val relativeStatisticPath: String
    abstract val statisticType: StatisticType
    abstract val availableSpecifiers: Collection<String>

    fun setPlayer(player: OfflinePlayer, subPath: String?, value: Int) {
        StatisticManager.setPlayer("$relativeStatisticPath${if(subPath != null) ".$subPath" else ""}", player, value)
    }

    fun getPlayer(player: OfflinePlayer, subPath: String?, day: Long): Int {
        return StatisticManager.getPlayer("$relativeStatisticPath${if (subPath != null) ".$subPath" else ""}", player, day)
    }

    fun incrementPlayer(player: OfflinePlayer, subPath: String?, value: Int) {
        StatisticManager.incrementPlayer("$relativeStatisticPath${if (subPath != null) ".$subPath" else ""}", player, value)
    }

    fun setServer(subPath: String?, value: Int) {
        StatisticManager.setServer("$relativeStatisticPath${if (subPath != null) ".$subPath" else ""}", value)
    }

    fun getServer(day: Long): Int {
        return StatisticManager.getServer(relativeStatisticPath, day)
    }

    fun incrementServer(subPath: String?, value: Int) {
        StatisticManager.incrementServer("$relativeStatisticPath${if(subPath != null) ".$subPath" else ""}", value)
    }

    fun register() {
        StatisticManager.registerListener(this)
    }
}