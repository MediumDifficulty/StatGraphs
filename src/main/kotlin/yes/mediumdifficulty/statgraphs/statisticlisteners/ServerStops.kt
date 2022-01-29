package yes.mediumdifficulty.statgraphs.statisticlisteners

import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class ServerStops: AbstractStatisticListener() {
    override val name = "stops"
    override val relativeStatisticPath = "stops"
    override val statisticType = StatisticType.SERVER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    fun stop() {
        incrementServer(null, 1)
    }
}