package yes.mediumdifficulty.statgraphs

import org.bukkit.plugin.java.JavaPlugin
import yes.mediumdifficulty.statgraphs.commands.CommandChart
import yes.mediumdifficulty.statgraphs.commands.TabCompleterChart
import yes.mediumdifficulty.statgraphs.files.FileStats
import yes.mediumdifficulty.statgraphs.files.FileUUIDs
import yes.mediumdifficulty.statgraphs.statisticlisteners.*

class StatGraphs: JavaPlugin() {
    override fun onEnable() {
        config.options().copyDefaults()
        saveDefaultConfig()

        FileStats.init()
        FileUUIDs.init()

        PlayerUUIDManager.init()
        registerStatisticListeners()
        registerCommands()
        registerEvents()
        enableMetrics()

        (StatisticManager.serverStatisticListeners.find { it.name == "starts" } as ServerStarts).start()
    }

    override fun onDisable() {
        (StatisticManager.serverStatisticListeners.find { it.name == "stops" } as ServerStops).stop()
    }

    private fun registerStatisticListeners() {
        PlayerBlockBreak().register()
        PlayerBlockPlace().register()
        PlayerCraftItem().register()
        PlayerDeath().register()
        PlayerConnect().register()
        PlayerDisconnect().register()
        PlayerEntityKill().register()

        ServerLogins().register()
        ServerDisconnects().register()
        ServerStarts().register()
        ServerStops().register()
        ServerNewPlayers().register()
    }

    private fun enableMetrics() {
        if(config.getBoolean("metrics-enabled")) {
            Metrics(this, 12914)
        } else {
            logger.warning("Not enabling metrics as 'metrics-enabled' is set to false in config.yml!")
        }
    }

    private fun registerCommands() {
        getCommand("chart")!!.setExecutor(CommandChart())
        getCommand("chart")!!.setTabCompleter(TabCompleterChart())
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(PlayerUUIDManager, this)
    }
}