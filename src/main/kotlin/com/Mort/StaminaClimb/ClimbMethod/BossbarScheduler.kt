package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ClimbMethod.Climb.finish
import com.Mort.StaminaClimb.ClimbMethod.Climb.regen
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.bossbarMap
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.timeLimit
import com.Mort.StaminaClimb.Listeners.Listener.Companion.checkAble
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector

class BossbarScheduler(val p: Player) : Runnable {
    // 루콘 최고

    private var progress = 1.0
    private val time = progress / (timeLimit.toDouble() * 20)
    private var bossbar = BossBar.bossBar(text(" "), progress.toFloat(), BossBar.Color.GREEN, BossBar.Overlay.PROGRESS)
    override fun run() {
        if (checkAble(p.location)) {
            bossbarMap[p] = bossbar
            p.velocity = Vector(0.0, 0.1, 0.0)
            p.fallDistance = 0f
            progress -= time
            if (progress <= 0) {
                regen(p)
            } else {
                changeColorAndProgress()
                showToPlayer()
            }
        } else {
            regen(p)
        }
    }

    private fun changeColorAndProgress() {
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

    private fun showToPlayer() {
        p.showBossBar(bossbar)
    }
}