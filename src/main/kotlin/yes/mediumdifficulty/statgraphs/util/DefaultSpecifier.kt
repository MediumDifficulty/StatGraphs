package yes.mediumdifficulty.statgraphs.util

import org.bukkit.Material
import org.bukkit.entity.EntityType

enum class DefaultSpecifier(val specifiers: Collection<String>) {
    BLOCKS(Material.values().filter { it.isBlock }.map { it.name }),
    ITEMS(Material.values().filter { it.isItem }.map { it.name }),
    ENTITIES(EntityType.values().map { it.name }),
    ANY_MATERIAL(Material.values().map { it.name }),
    NONE(emptyList())
}