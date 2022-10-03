package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ClimbMethod.Climb.finish
import com.Mort.StaminaClimb.ConfigDataFile.DataResource
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.bossbarMap
import net.kyori.adventure.bossbar.BossBar
import org.bukkit.entity.Player

class RegenScheduler(val p: Player) : Runnable {
    val bossbar = bossbarMap[p]!!
    private var progress = bossbar.progress().toDouble()
    private val time = (1 / (DataResource.timeLimit.toDouble() * 20)) / 2

    override fun run() {
        bossbarMap[p] = bossbar
        progress += time
        if (progress >= 1) {
            finish(p)
        } else {
            update()
        }
    }

    fun update() {
        bossbar.color(when {
            progress > 0.6 -> {
                BossBar.Color.GREEN
            }
            progress > 0.3 -> {
                BossBar.Color.YELLOW
            }
            progress <= 0.3 -> {
                BossBar.Color.RED
            }
            else -> {
                BossBar.Color.WHITE
            }
        })

        bossbar.progress(progress.toFloat())
    }
}