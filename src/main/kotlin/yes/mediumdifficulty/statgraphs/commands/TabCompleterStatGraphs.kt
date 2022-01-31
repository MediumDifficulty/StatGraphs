package yes.mediumdifficulty.statgraphs.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import java.util.*

class TabCompleterStatGraphs: TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if(args.size == 1)
            return checkAlreadyCompleted(listOf("reload"), args[0].lowercase(Locale.getDefault())).toMutableList()

        return emptyList<String>().toMutableList()
    }

    private fun checkAlreadyCompleted(library: Collection<String>, typed: String): Collection<String> {
        return library.filter { it.startsWith(typed) }
    }
}