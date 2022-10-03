package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import com.Mort.StaminaClimb.MainCore.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object DisplayMethod {

    fun bossbar(p: Player) {
        val task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, BossbarScheduler(p), 0L, 1L)
        taskIdMap[p] = task.taskId
    }
    fun regenbossbar(p: Player) {
        val task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, RegenScheduler(p), 0L, 1L)
        taskIdMap[p] = task.taskId
    }
    fun joinBossbar(p: Player) {
        val task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, JoinBossBarScheduler(p), 0L, 1L)
        taskIdMap[p] = task.taskId
    }
}