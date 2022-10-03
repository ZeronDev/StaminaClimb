package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ClimbMethod.DisplayMethod.bossbar
import com.Mort.StaminaClimb.ClimbMethod.DisplayMethod.joinBossbar
import com.Mort.StaminaClimb.ClimbMethod.DisplayMethod.regenbossbar
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.bossbarMap
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object Climb {
    // 루콘님 화이팅
    fun climb(p: Player) {
       bossbar(p)
    }
    fun finish(p: Player) {
        if (bossbarMap.containsKey(p)) {
            p.hideBossBar(bossbarMap[p]!!)
            Bukkit.getScheduler().cancelTask(taskIdMap[p]!!)
            taskIdMap.remove(p)
            bossbarMap.remove(p)
        } else if (taskIdMap.containsKey(p)) {
            Bukkit.getScheduler().cancelTask(taskIdMap[p]!!)
            taskIdMap.remove(p)
            p.sendActionBar(Component.text(" "))
        }
    }
    fun regen(p: Player) {
        if (taskIdMap.containsKey(p)) {
            Bukkit.getScheduler().cancelTask(taskIdMap[p]!!)
            taskIdMap.remove(p)
            regenbossbar(p)
        }
    }
    fun join(p: Player) {
        if (taskIdMap.containsKey(p)) {
            Bukkit.getScheduler().cancelTask(taskIdMap[p]!!)
            taskIdMap.remove(p)
            joinBossbar(p)
        }
    }
}