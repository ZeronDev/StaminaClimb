package com.Mort.StaminaClimb.ConfigDataFile

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

object DataResource {
    var prefix = "[ §eCLIMB §f]"
    var timeLimit = 10
    val unable: MutableList<Material> = mutableListOf(Material.LAVA, Material.WATER, Material.VOID_AIR, Material.CAVE_AIR, Material.AIR)
    val sneakList: MutableList<Player> = mutableListOf()
    val climbPlayer: MutableMap<UUID, Boolean> = mutableMapOf()
    val taskIdMap: MutableMap<Player, Int> = mutableMapOf()
    val bossbarMap: MutableMap<Player, BossBar> = mutableMapOf()

    fun checkArgsSize(p: Player, argsSize: Int, size: Int, message: String, context: () -> Unit) {
        if (argsSize != size) {
            p.sendMessage(text(message))
            return
        }
        context()
    }
}
