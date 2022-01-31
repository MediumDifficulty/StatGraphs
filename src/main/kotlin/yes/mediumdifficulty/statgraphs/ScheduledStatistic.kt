package yes.mediumdifficulty.statgraphs

interface ScheduledStatistic {
    val updateInterval: Long
    fun update()
}