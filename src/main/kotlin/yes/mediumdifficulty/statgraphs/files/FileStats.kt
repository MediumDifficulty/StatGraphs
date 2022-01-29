package yes.mediumdifficulty.statgraphs.files

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

object FileStats {
    private var file: File? = null
    var stats: FileConfiguration? = null
    private val plugin: Plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!
    fun save() {
        stats!!.save(file!!)
    }

    fun reload() {
        stats = YamlConfiguration.loadConfiguration(file!!)
    }

    fun init() {
        file = File(plugin.dataFolder, "stats.yml")
        if(!file!!.exists()) {
            file!!.createNewFile()
        }
        stats = YamlConfiguration.loadConfiguration(file!!)
    }
}