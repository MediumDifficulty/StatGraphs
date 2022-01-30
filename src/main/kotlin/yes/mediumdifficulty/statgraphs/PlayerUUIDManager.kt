package yes.mediumdifficulty.statgraphs

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import yes.mediumdifficulty.statgraphs.files.FileUUIDs
import java.util.*
import kotlin.collections.HashMap

object PlayerUUIDManager: Listener {
    private val namedUUIDs = HashMap<String, UUID>()

    val UUIDs: Collection<UUID>
        get() { return namedUUIDs.values }
    val playerNames: Collection<String>
        get() { return namedUUIDs.keys }

    @EventHandler
    private fun onJoin(e: PlayerJoinEvent) {
        store(e.player.name, e.player.uniqueId)
    }

    fun store(name: String, uuid: UUID) {
        FileUUIDs.UUIDs!!.set(uuid.toString(), name)
        FileUUIDs.save()
        reload()
    }

    fun reload() {
        namedUUIDs.clear()
        init()
    }

    fun init() {
        FileUUIDs.UUIDs!!.getKeys(false).forEach {
            namedUUIDs.set(FileUUIDs.UUIDs!!.getString(it)!!, UUID.fromString(it))
        }
    }

    fun getUUIDFromName(name: String): UUID? {
        return namedUUIDs[name]
    }

    fun getNames(): Collection<String> {
        return namedUUIDs.keys
    }
}