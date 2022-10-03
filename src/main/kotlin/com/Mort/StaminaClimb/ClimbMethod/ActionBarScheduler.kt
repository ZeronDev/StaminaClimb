package com.Mort.StaminaClimb.ClimbMethod

import com.Mort.StaminaClimb.ClimbMethod.Climb.finish
import com.Mort.StaminaClimb.ConfigDataFile.DataResource
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.taskIdMap
import com.Mort.StaminaClimb.ConfigDataFile.DataResource.timeLimit
import com.Mort.StaminaClimb.Listeners.Listener
import com.Mort.StaminaClimb.Listeners.Listener.Companion.checkAble
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import kotlin.math.roundToInt

class ActionBarScheduler(val p: Player) : Runnable {
    // 10 개 최대
    var progress = 10.0
    private val time = progress / (timeLimit.toDouble() * 20)

    override fun run() {
        if (checkAble(p.location)) {
            p.velocity = Vector(0.0, 0.1, 0.0)
            progress -= time
            if (progress <= 0) {
                if (taskIdMap.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask(taskIdMap[p]!!)
                    taskIdMap.remove(p)
                }
            } else {
                sendActionBar()
            }
        } else {
            p.sendActionBar(text(" "))
        }
    }

    fun sendActionBar() {
        val len = progress.roundToInt()
        val colorcode = when {
            len > 8 -> {
                "§2"
            }
            len > 6 -> {
                "§a"
            }
            len > 4 -> {
                "§e"
            }
            len > 2 -> {
                "§c"
            }
            else -> {
                "§4"
            }
        }
        val message = colorcode+"◼".repeat(len)
        p.sendActionBar(text(message))
    }
}