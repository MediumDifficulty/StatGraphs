package yes.mediumdifficulty.statgraphs

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.event.Listener
import org.bukkit.scheduler.BukkitRunnable
import yes.mediumdifficulty.statgraphs.files.FileStats
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

object StatisticManager {
    private val plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!

    val playerStatisticListeners = emptyList<AbstractStatisticListener>().toMutableList()
    val serverStatisticListeners = emptyList<AbstractStatisticListener>().toMutableList()

    fun registerListener(listener: AbstractStatisticListener) {
        if (listener is Listener) {
            Bukkit.getServer().pluginManager.registerEvents(listener, plugin)
        }
        if(listener is ScheduledStatistic) {
            object: BukkitRunnable() {
                override fun run() {
                    listener.update()
                }
            }.runTaskTimer(plugin, 0, listener.updateInterval)
        }


        plugin.logger.info("Registered statistic: ${listener.statisticType.name.lowercase(Locale.getDefault())}.${listener.name}")

        when(listener.statisticType) {
            StatisticType.PLAYER -> playerStatisticListeners.add(listener)
            StatisticType.SERVER -> serverStatisticListeners.add(listener)
        }
    }

    fun findPlayerStatistic(name: String): AbstractStatisticListener? {
        return playerStatisticListeners.find { it.name == name }
    }

    fun findServerStatistic(name: String): AbstractStatisticListener? {
        return serverStatisticListeners.find { it.name == name }
    }

    fun setStatistic(path: String, value: Int) {
        val day = System.currentTimeMillis().milliseconds.inWholeDays
        FileStats.stats!!.set("$day.$path", value)
        FileStats.save()
    }

    fun getStatistic(path: String, day: Long): Int {
        return FileStats.stats!!.getInt("$day.$path")
    }

    fun setPlayer(relativePath: String, player: OfflinePlayer, value: Int) {
        setStatistic("player.${player.uniqueId}.$relativePath", value)
    }

    fun getPlayer(relativePath: String, player: OfflinePlayer, day: Long): Int {
        return getStatistic("player.${player.uniqueId}.$relativePath", day)
    }

    fun setServer(relativePath: String, value: Int) {
        setStatistic("server.$relativePath", value)
    }

    fun getServer(relativePath: String, day: Long): Int {
        return getStatistic("server.$relativePath", day)
    }

    fun increment(path: String, value: Int) {
        val amount: Int
        val day = System.currentTimeMillis().milliseconds.inWholeDays
        val _path = "$day.$path"
        if (FileStats.stats!!.contains(_path)) {
            amount = FileStats.stats!!.getInt(_path)
            setStatistic(path, amount + value)
        } else {
            setStatistic(path, value)
        }
        FileStats.save()
    }

    fun incrementPlayer(relativePath: String, player: OfflinePlayer, value: Int) {
        increment("player.${player.uniqueId}.$relativePath", value)
    }

    fun incrementServer(relativePath: String, value: Int) {
        increment("server.$relativePath", value)
    }
}