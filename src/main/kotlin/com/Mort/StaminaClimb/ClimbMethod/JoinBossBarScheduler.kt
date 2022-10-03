package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ClimbMethod.Climb.regen
import com.Mort.StaminaClimb.ConfigDataFile.DataResource
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.bossbarMap
import com.Mort.StaminaClimb.Listeners.Listener
import net.kyori.adventure.bossbar.BossBar
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class JoinBossBarScheduler(val p: Player) : Runnable {
    // 저도 시참에 참여하고 싶지만 전 잼민이라 ㅋㅋ큐ㅠㅠ
    private var bossbar = bossbarMap[p]!!
    private var progress = bossbar.progress().toDouble()
    private val time = 1 / (DataResource.timeLimit.toDouble() * 20)
    override fun run() {
        if (Listener.checkAble(p.location)) {
            bossbarMap[p] = bossbar
            p.fallDistance = 0f
            p.velocity = Vector(0.0, 0.2, 0.0)
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