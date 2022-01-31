package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.Bukkit
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.ScheduledStatistic
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier
import yes.mediumdifficulty.statgraphs.vault.VaultManager

class PlayerVaultBalance: AbstractStatisticListener(), ScheduledStatistic {
    override val name = "vault_balance"
    override val relativeStatisticPath = "vaultBalance"
    override val statisticType = StatisticType.PLAYER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers
    override val updateInterval = 20L

    override fun update() {
        Bukkit.getOnlinePlayers().forEach { player ->
            setPlayer(player, null, VaultManager.economy.getBalance(player).toInt())
        }
    }
}