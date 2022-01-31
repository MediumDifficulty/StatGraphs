package yes.mediumdifficulty.statgraphs.statisticlisteners

import org.bukkit.Bukkit
import yes.mediumdifficulty.statgraphs.AbstractStatisticListener
import yes.mediumdifficulty.statgraphs.PlayerUUIDManager
import yes.mediumdifficulty.statgraphs.ScheduledStatistic
import yes.mediumdifficulty.statgraphs.StatisticType
import yes.mediumdifficulty.statgraphs.util.DefaultSpecifier
import yes.mediumdifficulty.statgraphs.vault.VaultManager

class ServerVaultEcoTotal: AbstractStatisticListener(), ScheduledStatistic {
    override val name = "vault_eco_total"
    override val relativeStatisticPath = "vaultEcoTotal"
    override val statisticType = StatisticType.SERVER
    override val availableSpecifiers = DefaultSpecifier.NONE.specifiers
    override val updateInterval = 20L

    override fun update() {
        var total = 0
        PlayerUUIDManager.UUIDs.forEach { uuid ->
            total += VaultManager.economy.getBalance(Bukkit.getOfflinePlayer(uuid)).toInt()
        }
        setServer(null, total)
    }
}