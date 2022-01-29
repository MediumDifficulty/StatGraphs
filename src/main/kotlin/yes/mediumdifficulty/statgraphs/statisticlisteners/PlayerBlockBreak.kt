package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerBlockBreak: AbstractStatisticListener(), Listener {
    override val name = "break"
    override val relativeStatisticPath = "break"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.BLOCKS.specifiers

    @EventHandler
    fun blockBreak(e: BlockBreakEvent) {
        incrementPlayer(e.player, e.block.type.name, 1)
    }
}