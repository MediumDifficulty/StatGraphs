package yes.mediumdifficulty.statgraphs.files

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

object FileUUIDs {
    private var file: File? = null
    var UUIDs: FileConfiguration? = null
    private val plugin: Plugin = Bukkit.getPluginManager().getPlugin("StatGraphs")!!
    fun save() {
        UUIDs!!.save(file!!)
    }

    fun reload() {
        UUIDs = YamlConfiguration.loadConfiguration(file!!)
    }

    fun init() {
        file = File(plugin.dataFolder, "uuids.yml")
        if(!file!!.exists()) {
            file!!.createNewFile()
        }
        UUIDs = YamlConfiguration.loadConfiguration(file!!)
    }
}