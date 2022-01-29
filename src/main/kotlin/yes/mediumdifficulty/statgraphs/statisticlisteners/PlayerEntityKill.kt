package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier

class PlayerEntityKill: AbstractStatisticListener(), Listener {
    override val name = "kill"
    override val relativeStatisticPath = "kill"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.ENTITIES.specifiers

    @EventHandler
    fun onKillEntity(e: EntityDamageByEntityEvent) {
        if(e.damager !is Player) return
        if(e.entity !is LivingEntity) return
        if((e.entity as LivingEntity).health - e.finalDamage > 0) return

        incrementPlayer(e.damager as Player, e.entity.type.name, 1)
    }
}