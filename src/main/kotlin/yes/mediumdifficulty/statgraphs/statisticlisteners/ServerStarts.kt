package yes.mediumdifficulty.statgraphs.statisticlisteners

import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class ServerStarts: AbstractStatisticListener() {
    override val name = "starts"
    override val relativeStatisticPath = "starts"
    override val statisticType = StatisticType.SERVER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers

    fun start() {
        incrementServer(null, 1)
    }
}