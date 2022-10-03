package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import com.Mort.StaminaClimb.MainCore.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object DisplayMethod {
    // 근데 솔직히 RPG 시리즈는 별로 재미없어여 ㅎㅎ; (ㅈㅅㅈㅅ)

    fun bossbar(p: Player) {
        val task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, BossbarScheduler(p), 0L, 1L)
        taskIdMap[p] = task.taskId
    }
    fun actionbar(p: Player) {
        val task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, ActionBarScheduler(p), 0L, 1L)
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