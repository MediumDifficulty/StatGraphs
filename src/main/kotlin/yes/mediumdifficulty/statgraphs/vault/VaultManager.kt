package yes.mediumdifficulty.statgraphs.vault

import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit

object VaultManager {
    var isEnabled = false
    private val plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!
    private val log = plugin.logger
    private var vaultEconomy: Economy? = null

    val economyEnabled: Boolean
        get() { return vaultEconomy != null && isEnabled }

    val economy: Economy
        get() { return vaultEconomy!! }

    fun enable() {
        val economyProvider = Bukkit.getServer().servicesManager.getRegistration(Economy::class.java)
        if(economyProvider == null) {
            log.warning("Found Vault dependency but no economy plugin. Not enabling economy statistics")
        } else
            vaultEconomy = economyProvider.provider

        isEnabled = true
    }
}