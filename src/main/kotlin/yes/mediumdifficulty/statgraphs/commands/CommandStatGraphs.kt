package yes.mediumdifficulty.statgraphs.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import yes.mediumdifficulty.statgraphs.files.FileStats
import yes.mediumdifficulty.statgraphs.files.FileUUIDs

class CommandStatGraphs: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!
        if(args.isEmpty()) return false
        if(args[0] == "reload") {
            plugin.reloadConfig()
            FileStats.reload()
            FileUUIDs.reload()
            sender.sendMessage("${ChatColor.GREEN}Successfully reloaded StatGraphs!")
        }
        return true
    }
}