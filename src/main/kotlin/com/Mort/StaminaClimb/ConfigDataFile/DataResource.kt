package com.Mort.StaminaClimb.ConfigDataFile

import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component.text
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

object DataResource {
    var prefix = "[ §6CLIMB §f]"
    var timeLimit = 10
    val unable: MutableList<Material> = mutableListOf(Material.LAVA, Material.WATER, Material.VOID_AIR, Material.CAVE_AIR, Material.AIR)
    val sneakList: MutableList<Player> = mutableListOf()
    val taskIdMap: MutableMap<Player, Int> = mutableMapOf()
    val bossbarMap: MutableMap<Player, BossBar> = mutableMapOf()
}
