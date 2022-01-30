package yes.mediumdifficulty.statgraphs.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import yes.mediumdifficulty.statgraphs.StatisticManager
import yes.mediumdifficulty.statgraphs.PlayerUUIDManager
import java.util.*

class TabCompleterChart: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        val uArgs = args.map { it.uppercase(Locale.getDefault()) }
        val lArgs = args.map { it.lowercase(Locale.getDefault()) }

        if(args.size == 1) return checkAlreadyCompleted(listOf("server", "player"), lArgs[0]).toMutableList()

        if(args[0] == "server" && args.size == 2) return checkAlreadyCompleted(StatisticManager.serverStatisticListeners.map { it.name }, lArgs[1]).toMutableList()
        if(args[0] == "player" && args.size == 2) return checkAlreadyCompleted(StatisticManager.playerStatisticListeners.map { it.name }, lArgs[1]).toMutableList()

        if(args[0] == "player" && args.size == 3) {
            return checkAlreadyCompleted(PlayerUUIDManager.getNames(), args[2]).toMutableList()
        }

        if(args[0] == "player" && args.size == 4) {
            val statistic = StatisticManager.playerStatisticListeners.find { it.name == lArgs[1] }
            if(statistic == null) return null
            return checkAlreadyCompleted(statistic.availableSpecifiers.toMutableList(), uArgs[3]).toMutableList()
        }

        return null
    }

    private fun checkAlreadyCompleted(library: Collection<String>, typed: String): Collection<String> {
        return library.filter { it.startsWith(typed) }
    }
}