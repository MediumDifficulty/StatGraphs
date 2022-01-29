package yes.mediumdifficulty.statgraphs.commands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticManager
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.UUIDManager
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.max
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.milliseconds

class CommandChart: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if(args.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}Please provide valid command arguments")
            return false
        }

        when(args.first()) {
            "server" -> subCategoryStatistic(sender, args.drop(1), StatisticManager.serverStatisticListeners)
            "player" -> subCategoryStatistic(sender, args.drop(1), StatisticManager.playerStatisticListeners)
            else -> {
                sender.sendMessage("${ChatColor.RED}Please enter a valid category")
                return false
            }
        }
        return true
    }

    private fun subCategoryStatistic(sender: CommandSender, args: List<String>, category: Collection<AbstractStatisticListener>) {
        if(args.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}Please provide a valid statistic")
            return
        }
        val foundStatistic = category.find { it.name == args[0] }
        if(foundStatistic == null) {
            sender.sendMessage("${ChatColor.RED}Please provide a valid statistic")
            return
        }
        if(args.size == 1) {
            sender.sendMessage("${ChatColor.RED}Please provide a player name")
            return
        }
        if(foundStatistic.statisticType == StatisticType.PLAYER) {
            val foundPLayerUUID = UUIDManager.getUUIDFromName(args[1])
            if(foundPLayerUUID == null) {
                sender.sendMessage("${ChatColor.RED}Please provide a player name")
                return
            }
            sender.spigot().sendMessage(*render(foundStatistic, args, foundPLayerUUID))
        }

    }

    private fun render(statistic: AbstractStatisticListener, args: List<String>, playerUUID: UUID?): Array<BaseComponent> {
        val plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!
        val config = plugin.config

        val chartWidth = config.getInt("chart.width")
        val chartHeight = config.getInt("chart.height")

        val currentDay = System.currentTimeMillis().milliseconds.inWholeDays

        val data = Array(chartWidth) {
            when(statistic.statisticType) {
                StatisticType.SERVER -> statistic.getServer(currentDay - chartWidth + it + 1)
                StatisticType.PLAYER -> statistic.getPlayer(Bukkit.getOfflinePlayer(playerUUID!!), if(args.size < 3) null else args[2], currentDay - chartWidth + it + 1)
            }
        }

        val maxValue = max(data.maxOrNull()!!, 1)
        val scaledData = data.map {
            it * (chartHeight - 1) / maxValue
        }

        var out = Component.text()
            .append(Component.text("${statistic.statisticType.name.lowercase(Locale.getDefault())} statistic: ${statistic.name} from last $chartWidth days\n")
                .color(NamedTextColor.GOLD)
            )
        for(y in chartHeight - 1 downTo 0) {
            for(x in 0 until chartWidth) {
                var pixel = Component.text('X')
                if(calculatePixel(scaledData, x, y)) {
                    val day = (System.currentTimeMillis().milliseconds - chartWidth.days + x.days).inWholeMilliseconds
                    val pixelDescription = Component.text(SimpleDateFormat(plugin.config.getString("chart.date-format")!!).format(Date(day)) + "\n")
                        .color(NamedTextColor.GOLD)
                        .append(
                            Component.text("value: ${data[x]}")
                                .color(NamedTextColor.AQUA)
                        )
                    pixel = pixel.color(NamedTextColor.GREEN)
                        .hoverEvent(HoverEvent.showText(pixelDescription))
                } else {
                    pixel = pixel.color(NamedTextColor.GRAY)
                }
                out.append(pixel)
            }
            out = out.append(Component.text("\n"))
        }

        return BungeeComponentSerializer.get().serialize(out.build())
    }

    private fun calculatePixel(data: List<Int>, x: Int, y: Int): Boolean {
        return data[x] == y
    }
}